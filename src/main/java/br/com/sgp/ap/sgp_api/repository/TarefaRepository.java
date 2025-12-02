package br.com.sgp.ap.sgp_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.ap.sgp_api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

  List<Tarefa> findByDataConclusao(LocalDate dataConclusao);

  List<Tarefa> findByPrioridade(String prioridade);

  List<Tarefa> findByStatus(String status);

}
