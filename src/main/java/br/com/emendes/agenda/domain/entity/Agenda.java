package br.com.emendes.agenda.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "agenda")
@Entity
public class Agenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String descricao;
  @Column(name = "data_hora")
  private LocalDateTime horario;
  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;
  @ManyToOne
  private Paciente paciente;

  @Override
  public String toString() {
    return "Agenda{" +
        "id=" + id +
        ", descricao='" + descricao + '\'' +
        ", horario=" + horario +
        ", dataCriacao=" + dataCriacao +
        ", pacienteId=" + paciente.getId() +
        '}';
  }
}
