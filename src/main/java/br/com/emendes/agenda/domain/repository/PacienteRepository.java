package br.com.emendes.agenda.domain.repository;

import br.com.emendes.agenda.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

  Optional<Paciente> findByCpf(String cpf);

}
