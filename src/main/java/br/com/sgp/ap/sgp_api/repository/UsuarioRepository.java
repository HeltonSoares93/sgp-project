package br.com.sgp.ap.sgp_api.repository;

import br.com.sgp.ap.sgp_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
