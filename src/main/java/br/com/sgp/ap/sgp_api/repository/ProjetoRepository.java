package br.com.sgp.ap.sgp_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.ap.sgp_api.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


  List<Projeto> findByStatus(String status);

  List<Projeto> findByDataConclusao(LocalDate dataConclusao);

}
