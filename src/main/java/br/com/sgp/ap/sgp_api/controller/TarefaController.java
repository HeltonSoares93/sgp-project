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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgp.ap.sgp_api.enums.TarefaPrioridadeEnum;
import br.com.sgp.ap.sgp_api.enums.TarefaStatusEnum;
import br.com.sgp.ap.sgp_api.model.Projeto;
import br.com.sgp.ap.sgp_api.model.Tarefa;
import br.com.sgp.ap.sgp_api.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

  @Autowired
  private TarefaService service;

  @GetMapping
  public ResponseEntity<List<Tarefa>> listarTarefas() {
    return ResponseEntity.ok().body(service.consultarTarefas());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Tarefa>> consultarTarefaPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(service.consultarTarefaPorId(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
    Optional<Tarefa> tarefaSeExistir = service.consultarTarefaPorId(id);
    if (tarefaSeExistir.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    tarefa.setIdTarefa(id);
    return ResponseEntity.ok().body(service.salvaTarefa(tarefa));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
    Optional<Tarefa> tarefaSeExistir = service.consultarTarefaPorId(id);
    if (tarefaSeExistir.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    service.deletar(id);

    return ResponseEntity.notFound().build();

  }

  @PostMapping
  public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefa) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaTarefa(tarefa));
  }

  @GetMapping("/busca-status")
  public ResponseEntity<List<Tarefa>> consultarTarefaPorStatus(@RequestParam("status") TarefaStatusEnum status) {
    List<Tarefa> tarefaExistente = service.consultarTarefaStatus(status);
    if (tarefaExistente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(service.consultarTarefaStatus(status));
  }

  @GetMapping("/busca-prioridade")
  public ResponseEntity<List<Tarefa>> consultarTarefaPorPrioridade(
      @RequestParam("prioridade") TarefaPrioridadeEnum prioridade) {
    List<Tarefa> tarefaExistente = service.consultarTarefaPrioridade(prioridade);

    if (tarefaExistente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(service.consultarTarefaPrioridade(prioridade));
  }

  // atualização 12 de dezembro de 2025 - busca de tarefas por agrupamento de
  // projetos
  @GetMapping("/buscar-projeto")
  public ResponseEntity<List<Tarefa>> consultarTarefaPorProjeto(@RequestParam("idProjeto") Long idProjeto) {
    Optional<Projeto> projetoExistente = service.buscarProjetoPorId(idProjeto);
    if (projetoExistente.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    List<Tarefa> tarefas = service.buscarTarefaPorProjeto(projetoExistente.get());
    return ResponseEntity.ok().body(tarefas);
  }

}
