package br.com.sgp.ap.sgp_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.ap.sgp_api.enums.TarefaPrioridadeEnum;
import br.com.sgp.ap.sgp_api.enums.TarefaStatusEnum;
import br.com.sgp.ap.sgp_api.model.Projeto;
import br.com.sgp.ap.sgp_api.model.Tarefa;
import br.com.sgp.ap.sgp_api.repository.ProjetoRepository;
import br.com.sgp.ap.sgp_api.repository.TarefaRepository;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository tarefaRepository;

  @Autowired
  private ProjetoRepository projetoRepository;

  public Tarefa salvaTarefa(Tarefa tarefa) {
    return tarefaRepository.save(tarefa);
  }

  public List<Tarefa> consultarTarefas() {
    return tarefaRepository.findAll();
  }

  public Optional<Tarefa> consultarTarefaPorId(Long id) {
    return tarefaRepository.findById(id);
  }

  public void deletar(Long id) {
    tarefaRepository.deleteById(id);
  }

  public List<Tarefa> consultarTarefaStatus(TarefaStatusEnum status) {
    return tarefaRepository.findByStatus(status);
  }

  public List<Tarefa> consultarTarefaPrioridade(TarefaPrioridadeEnum prioridade) {
    return tarefaRepository.findByPrioridade(prioridade);
  }

  public List<Tarefa> consultarPorDataConclusao(LocalDate dataConclusao) {
    return tarefaRepository.findByDataConclusao(dataConclusao);
  }

  public Optional<Projeto> buscarProjetoPorId(Long id) {
    return projetoRepository.findById(id);
  }

  public List<Tarefa> buscarTarefaPorProjeto(Projeto projeto) {
    return tarefaRepository.findByProjeto(projeto);
  }

}
