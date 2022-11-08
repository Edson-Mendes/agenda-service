package br.com.emendes.agenda.api.controller;

import br.com.emendes.agenda.domain.entity.Paciente;
import br.com.emendes.agenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  private final PacienteService pacienteService;

  @PostMapping
  public ResponseEntity<Paciente> save(@RequestBody Paciente paciente) {
    Paciente savedPaciente = pacienteService.save(paciente);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
  }

  @GetMapping
  public ResponseEntity<List<Paciente>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Paciente> findById(@PathVariable(name = "id") Long id) {
    Optional<Paciente> optPaciente = pacienteService.findById(id);
    if(optPaciente.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(optPaciente.get());
  }

  @PutMapping
  public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
    Paciente savedPaciente = pacienteService.save(paciente);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
    pacienteService.delete(id);
    return ResponseEntity.noContent().build();
  }


}
