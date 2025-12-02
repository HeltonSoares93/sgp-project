package br.com.sgp.ap.sgp_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.ap.sgp_api.model.Usuario;
import br.com.sgp.ap.sgp_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario salvarUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public List<Usuario> consultarUsuarios() {
    return usuarioRepository.findAll();
  }

  // o que é optional??
  public Optional<Usuario> consultarUsuarioId(Long id) {
    return usuarioRepository.findById(id);
  }

  public void deletar(Long id) {
    usuarioRepository.deleteById(id);
  }

  public Optional<Usuario> consultarUsuarioCpf(String cpf) {
    return usuarioRepository.findByCpf(cpf);
  }

  public Optional<Usuario> consultarUsuarioEmail(String email) {
    return usuarioRepository.findByEmail(email);
  }

}
