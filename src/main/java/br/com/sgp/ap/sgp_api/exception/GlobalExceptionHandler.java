package br.com.sgp.ap.sgp_api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.sgp.ap.sgp_api.dto.MensagemErroApi;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<MensagemErroApi> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

    List<String> erros = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(e -> e.getDefaultMessage())
        .collect(Collectors.toList());

    MensagemErroApi msgErroApi = new MensagemErroApi(HttpStatus.BAD_REQUEST, erros);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgErroApi);
  }

  @ExceptionHandler(UsuarioNaoEncontradoException.class)
  public ResponseEntity<MensagemErroApi> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex,
      WebRequest request) {

    MensagemErroApi msgErroApi = new MensagemErroApi(HttpStatus.NOT_FOUND, ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgErroApi);

  }
}