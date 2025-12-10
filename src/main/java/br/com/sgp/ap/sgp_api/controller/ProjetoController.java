package br.com.sgp.ap.sgp_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgp.ap.sgp_api.model.Projeto;
import br.com.sgp.ap.sgp_api.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

  @Autowired
  private ProjetoService service;

  @GetMapping
  public ResponseEntity<List<Projeto>> listarProjetos() {
    return ResponseEntity.ok().body(service.consultarProjetos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Projeto>> buscarProjetoPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(service.consultarProjetoId(id));
  }

  @PostMapping
  public ResponseEntity<Projeto> cadastrarProjeto(@RequestBody Projeto projeto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaProjeto(projeto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
    Optional<Projeto> projetoExistente = service.consultarProjetoId(id);
    if (projetoExistente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    projeto.setIdProjeto(id);
    return ResponseEntity.ok().body(service.salvaProjeto(projeto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirProjeto(@PathVariable("id") Long id) {
    Optional<Projeto> projetoExistente = service.consultarProjetoId(id);

    if (projetoExistente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    service.deletar(id);
    return ResponseEntity.notFound().build();
  }

}
