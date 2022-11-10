package br.com.emendes.agenda.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

  @NotBlank(message = "nome é obrigatório")
  private String nome;
  @NotBlank(message = "sobrenome é obrigatório")
  private String sobrenome;
  private String email;
  @NotBlank(message = "CPF é obrigatório")
  private String cpf;

}
