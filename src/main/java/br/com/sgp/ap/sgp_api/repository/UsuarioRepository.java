package br.com.sgp.ap.sgp_api.repository;

import java.util.Optional;

import br.com.sgp.ap.sgp_api.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByCpf(String cpf);

  Optional<Usuario> findByEmail(String email);

}
