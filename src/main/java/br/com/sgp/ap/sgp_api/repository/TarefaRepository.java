package br.com.sgp.ap.sgp_api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.ap.sgp_api.enums.TarefaPrioridadeEnum;
import br.com.sgp.ap.sgp_api.enums.TarefaStatusEnum;
import br.com.sgp.ap.sgp_api.model.Projeto;
import br.com.sgp.ap.sgp_api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

  List<Tarefa> findByDataConclusao(LocalDate dataConclusao);

  List<Tarefa> findByPrioridade(TarefaPrioridadeEnum prioridade);

  List<Tarefa> findByStatus(TarefaStatusEnum status);

  List<Tarefa> findByProjeto(Projeto projeto);

}
