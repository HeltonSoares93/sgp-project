package br.com.sgp.ap.sgp_api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.ap.sgp_api.dto.UsuarioDTO;
import br.com.sgp.ap.sgp_api.enums.UsuarioStatus;
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

  public UsuarioDTO consultarUsuarioId(Long id) {
    Optional<Usuario> usuarioExisten = usuarioRepository.findById(id);
    if (usuarioExisten.isPresent()) {

      UsuarioDTO usuarioDTO = new UsuarioDTO();
      Usuario usuario = usuarioExisten.get();
      UsuarioStatus status = usuario.getStatus();
      LocalDate dataNascimento = usuario.getDataNascimento();
      LocalDate dataAtual = LocalDate.now();

      Period p = Period.between(dataAtual, dataAtual);
      Integer idade = p.getYears();

      String cpfCadastrado = usuario.getCpf();
      String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);
      String cpfFormatado = tresPrimeirosIndices + ".***.***-**";

      usuarioDTO.setId(usuario.getId());
      usuarioDTO.setNome(usuario.getNome());
      usuarioDTO.setEmail(usuario.getEmail());
      usuarioDTO.setDataNascimento(usuario.getDataNascimento());
      usuarioDTO.setIdade(idade);
      usuarioDTO.setCpf(cpfFormatado);

      String statusString = status.toString();

      String primeiroCaractere = statusString.substring(0, 1).toUpperCase();
      String demaisCaracteres = statusString.substring(1).toLowerCase();
      String statusFormatado = primeiroCaractere + demaisCaracteres;
      usuarioDTO.setStatus(statusFormatado);

      return usuarioDTO;
    }
    return null;
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
