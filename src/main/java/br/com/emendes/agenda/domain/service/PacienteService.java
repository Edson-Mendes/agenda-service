package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

  Paciente save(Paciente paciente);

  void delete(Long id);

  List<Paciente> listAll();

 Optional<Paciente> findById(Long id);

}
