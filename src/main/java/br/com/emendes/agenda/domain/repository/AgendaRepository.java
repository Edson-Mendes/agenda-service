package br.com.emendes.agenda.domain.repository;

import br.com.emendes.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {


  Optional<Agenda> findByHorario(LocalDateTime horario);
}
