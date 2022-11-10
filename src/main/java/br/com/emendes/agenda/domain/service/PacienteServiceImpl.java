package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Paciente;
import br.com.emendes.agenda.domain.repository.PacienteRepository;
import br.com.emendes.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

  private final PacienteRepository pacienteRepository;

  @Override
  public Paciente save(Paciente paciente) {
    Optional<Paciente> optPaciente = pacienteRepository.findByCpf(paciente.getCpf());

    if (optPaciente.isPresent()) {
      if (!optPaciente.get().getId().equals(paciente.getId())) {
        throw new BusinessException("Cpf já cadastrado.");
      }
    }

    return pacienteRepository.save(paciente);
  }

  @Override
  public Paciente alterar(Long id, Paciente paciente) {
    Optional<Paciente> optPaciente = this.findById(id);

    if (optPaciente.isEmpty()) {
      throw new BusinessException("Paciente não cadastrado!");
    }

    paciente.setId(id);

    return save(paciente);
  }

  @Override
  public void delete(Long id) {
    pacienteRepository.deleteById(id);
  }

  @Override
  public List<Paciente> listAll() {
    return pacienteRepository.findAll();
  }

  @Override
  public Optional<Paciente> findById(Long id) {
    return pacienteRepository.findById(id);
  }

}
