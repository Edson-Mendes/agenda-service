package br.com.emendes.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponse {

  private Long id;
  private String descricao;
  private LocalDateTime horario;
  private LocalDateTime dataCriacao;
  private PacienteResponse paciente;

}
