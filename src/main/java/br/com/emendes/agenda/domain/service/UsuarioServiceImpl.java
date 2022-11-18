package br.com.emendes.agenda.domain.service;

import br.com.emendes.agenda.domain.entity.Usuario;
import br.com.emendes.agenda.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
    Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(usuario);

    if (optUsuario.isEmpty()) {
      throw new UsernameNotFoundException("Usuario não encontrado");
    }

    Usuario user = optUsuario.get();

    return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
  }

  @Override
  public List<Usuario> getAll() {
    return usuarioRepository.findAll();
  }

  @Override
  public Usuario save(Usuario usuario) {
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    return usuarioRepository.save(usuario);
  }
}
