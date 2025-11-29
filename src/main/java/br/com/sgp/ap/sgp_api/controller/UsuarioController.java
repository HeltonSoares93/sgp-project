package br.com.sgp.ap.sgp_api.controller;

import br.com.sgp.ap.sgp_api.model.Usuario;
import br.com.sgp.ap.sgp_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> encontrarTodos() {
        return repository.findAll();
    }


}
