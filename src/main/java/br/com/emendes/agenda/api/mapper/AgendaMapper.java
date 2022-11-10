package br.com.emendes.agenda.api.mapper;

import br.com.emendes.agenda.api.request.AgendaRequest;
import br.com.emendes.agenda.api.response.AgendaResponse;
import br.com.emendes.agenda.domain.entity.Agenda;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AgendaMapper {

  private final ModelMapper mapper;

  public Agenda toAgenda(AgendaRequest request) {
    return mapper.map(request, Agenda.class);
  }

  public AgendaResponse toAgendaResponse(Agenda agenda) {
    return mapper.map(agenda, AgendaResponse.class);
  }

  public List<AgendaResponse> toAgendaResponseList(List<Agenda> listAgenda) {
    return listAgenda.stream().map(p -> mapper.map(p, AgendaResponse.class)).toList();
  }
}
