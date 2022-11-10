package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Agenda;
import br.com.emendes.agenda.domain.entity.Paciente;
import br.com.emendes.agenda.domain.repository.AgendaRepository;
import br.com.emendes.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AgendaServiceImpl implements AgendaService {

  private final AgendaRepository agendaRepository;
  private final PacienteService pacienteService;

  @Override
  public List<Agenda> listAll() {
    return agendaRepository.findAll();
  }

  @Override
  public Optional<Agenda> findById(Long id) {
    return agendaRepository.findById(id);
  }

  @Override
  public Agenda save(Agenda agenda) {
    Optional<Paciente> optPaciente = pacienteService.findById(agenda.getPaciente().getId());

    if (optPaciente.isEmpty()) {
      throw new BusinessException("Paciente not found");
    }

    Optional<Agenda> optAgenda = agendaRepository.findByHorario(agenda.getHorario());

    if (optAgenda.isPresent()) {
      throw new BusinessException("Já existe agendamento deste horário");
    }

    agenda.setPaciente(optPaciente.get());
    agenda.setDataCriacao(LocalDateTime.now());

    return agendaRepository.save(agenda);
  }
}
