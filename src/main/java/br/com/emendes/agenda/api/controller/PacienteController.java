package br.com.emendes.agenda.api.controller;

import br.com.emendes.agenda.api.mapper.PacienteMapper;
import br.com.emendes.agenda.api.request.PacienteRequest;
import br.com.emendes.agenda.api.response.PacienteResponse;
import br.com.emendes.agenda.domain.entity.Paciente;
import br.com.emendes.agenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  private final PacienteService pacienteService;
  private final PacienteMapper mapper;

  @PostMapping
  public ResponseEntity<PacienteResponse> save(@RequestBody @Valid PacienteRequest pacienteRequest) {

    Paciente savedPaciente = pacienteService.save(mapper.toPaciente(pacienteRequest));

    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(savedPaciente));
  }

  @GetMapping
  public ResponseEntity<List<PacienteResponse>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponseList(pacienteService.listAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteResponse> findById(@PathVariable(name = "id") Long id) {
    Optional<Paciente> optPaciente = pacienteService.findById(id);
    if (optPaciente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(optPaciente.get()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PacienteResponse> update(
      @PathVariable(name = "id") Long id, @RequestBody PacienteRequest pacienteRequest) {
    Paciente savedPaciente = pacienteService.alterar(id, mapper.toPaciente(pacienteRequest));

    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(savedPaciente));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
    pacienteService.delete(id);
    return ResponseEntity.noContent().build();
  }


}
