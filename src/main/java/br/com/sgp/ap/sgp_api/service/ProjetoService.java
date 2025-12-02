package br.com.sgp.ap.sgp_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.ap.sgp_api.model.Projeto;
import br.com.sgp.ap.sgp_api.repository.ProjetoRepository;

@Service
public class ProjetoService {

  @Autowired
  private ProjetoRepository projetoRepository;

  public Projeto salvaProjeto(Projeto projeto) {
    return projetoRepository.save(projeto);
  }

  public List<Projeto> consultarProjetos() {
    return projetoRepository.findAll();
  }

  public Optional<Projeto> consultarProjetoId(Long id) {
    return projetoRepository.findById(id);
  }

  public void deletar(Long id) {
    projetoRepository.deleteById(id);
  }

  public List<Projeto> consultarProjetoStatus(String status) {
    return projetoRepository.findByStatus(status);
  }

  public List<Projeto> consultarPorDataConclusao(LocalDate dataConclusao) {
    return projetoRepository.findByDataConclusao(dataConclusao);
  }

}
