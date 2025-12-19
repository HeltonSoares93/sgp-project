package br.com.sgp.ap.sgp_api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

  public UsuarioNaoEncontradoException(Long id) {
    super(String.format("Usuario de ID %d não encontrado", id));
  }

  

}
