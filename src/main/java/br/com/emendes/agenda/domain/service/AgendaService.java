package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Agenda;

import java.util.List;
import java.util.Optional;

public interface AgendaService {

  List<Agenda> listAll();

  Optional<Agenda> findById(Long id);

  Agenda save(Agenda agenda);

}
