package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {

  List<Usuario> getAll();

  Usuario save(Usuario usuario);

}
