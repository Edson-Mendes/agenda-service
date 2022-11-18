package br.com.emendes.agenda.unit;

import br.com.emendes.agenda.domain.entity.Agenda;
import br.com.emendes.agenda.domain.entity.Paciente;
import br.com.emendes.agenda.domain.repository.AgendaRepository;
import br.com.emendes.agenda.domain.service.AgendaServiceImpl;
import br.com.emendes.agenda.domain.service.PacienteService;
import br.com.emendes.agenda.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AgendaServiceTest {

  @InjectMocks
  private AgendaServiceImpl agendaService;

  @Mock
  private PacienteService pacienteService;
  @Mock
  private AgendaRepository agendaRepository;
  @Captor
  ArgumentCaptor<Agenda>  agendaCaptor;

  @Test
  @DisplayName("Deve salvar agendamento com sucesso")
  void salvarComSucesso() {
    LocalDateTime now = LocalDateTime.now();
    Paciente paciente = new Paciente();
    paciente.setId(1L);
    paciente.setNome("paciente");

    Agenda agenda = new Agenda();
    agenda.setDescricao("Descrição do agendamento");
    agenda.setHorario(now);
    agenda.setPaciente(paciente);

    Mockito.when(pacienteService.findById(agenda.getPaciente().getId()))
        .thenReturn(Optional.of(paciente));
    Mockito.when(agendaRepository.findByHorario(now))
        .thenReturn(Optional.empty());

    agendaService.save(agenda);

    Mockito.verify(pacienteService).findById(agenda.getPaciente().getId());
    Mockito.verify(agendaRepository).findByHorario(now);
    Mockito.verify(agendaRepository).save(agendaCaptor.capture());

    Agenda agendaSalva = agendaCaptor.getValue();

    Assertions.assertThat(agendaSalva.getDataCriacao()).isNotNull();
    Assertions.assertThat(agendaSalva.getPaciente()).isNotNull();
  }

  @Test
  @DisplayName("Não deve salvar agendamento sem paciente")
  void salvarErroPacienteNaoEncontrado() {
    LocalDateTime now = LocalDateTime.now();
    Paciente paciente = new Paciente();
    paciente.setId(1L);
    paciente.setNome("paciente");

    Agenda agenda = new Agenda();
    agenda.setDescricao("Descrição do agendamento");
    agenda.setHorario(now);
    agenda.setPaciente(paciente);

    Assertions.assertThatExceptionOfType(BusinessException.class)
        .isThrownBy(() -> agendaService.save(agenda))
        .withMessage("Paciente not found");
  }

}
