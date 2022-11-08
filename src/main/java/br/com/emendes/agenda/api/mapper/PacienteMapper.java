package br.com.emendes.agenda.api.mapper;

import br.com.emendes.agenda.api.request.PacienteRequest;
import br.com.emendes.agenda.api.response.PacienteResponse;
import br.com.emendes.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PacienteMapper {

  private final ModelMapper modelMapper;

  public Paciente toPaciente(PacienteRequest request) {
    return modelMapper.map(request, Paciente.class);
  }

  public PacienteResponse toPacienteResponse(Paciente paciente) {
    return modelMapper.map(paciente, PacienteResponse.class);
  }

  public List<PacienteResponse> toPacienteResponseList(List<Paciente> listPaciente) {
    return listPaciente.stream().map(p -> modelMapper.map(p, PacienteResponse.class)).toList();
  }
}
