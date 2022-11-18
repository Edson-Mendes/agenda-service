package br.com.emendes.agenda.domain.repository;

import br.com.emendes.agenda.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByUsuario(String usuario);
}
