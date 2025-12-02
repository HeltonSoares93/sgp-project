package br.com.sgp.ap.sgp_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.ap.sgp_api.model.Tarefa;
import br.com.sgp.ap.sgp_api.repository.TarefaRepository;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository tarefaRepository;

  public Tarefa salvaProjeto(Tarefa tarefa) {
    return tarefaRepository.save(tarefa);
  }

  public List<Tarefa> consultarProjetos() {
    return tarefaRepository.findAll();
  }

  public Optional<Tarefa> consultarTarefaId(Long id) {
    return tarefaRepository.findById(id);
  }

  public void deletar(Long id) {
    tarefaRepository.deleteById(id);
  }

  public List<Tarefa> consultarTarefaStatus(String status) {
    return tarefaRepository.findByStatus(status);
  }

  public List<Tarefa> consultarPorDataConclusao(LocalDate dataConclusao) {
    return tarefaRepository.findByDataConclusao(dataConclusao);
  }
}
