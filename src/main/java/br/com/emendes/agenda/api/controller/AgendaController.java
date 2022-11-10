package br.com.emendes.agenda.api.controller;

import br.com.emendes.agenda.api.mapper.AgendaMapper;
import br.com.emendes.agenda.api.request.AgendaRequest;
import br.com.emendes.agenda.api.response.AgendaResponse;
import br.com.emendes.agenda.domain.entity.Agenda;
import br.com.emendes.agenda.domain.service.AgendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/agendas")
public class AgendaController {

  private final AgendaService agendaService;
  private final AgendaMapper mapper;

  @GetMapping
  public ResponseEntity<List<AgendaResponse>> listAll() {
    return ResponseEntity.ok(mapper.toAgendaResponseList(agendaService.listAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgendaResponse> findById(@PathVariable(name = "id") Long id) {
    Optional<Agenda> optAgenda = agendaService.findById(id);
    if (optAgenda.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(mapper.toAgendaResponse(optAgenda.get()));
  }

  @PostMapping
  public ResponseEntity<AgendaResponse> save(@RequestBody @Valid AgendaRequest agendaRequest) {
    Agenda agendaToBeSaved = mapper.toAgenda(agendaRequest);
    log.info("Agenda {}", agendaToBeSaved);
    Agenda savedAgenda = agendaService.save(agendaToBeSaved);

    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAgendaResponse(savedAgenda));
  }
}
