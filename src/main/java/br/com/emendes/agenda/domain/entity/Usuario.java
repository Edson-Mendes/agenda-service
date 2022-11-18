package br.com.emendes.agenda.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Table(name = "usuario")
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String nome;
  @NotBlank
  private String usuario;
  @NotBlank
  private String senha;

}
