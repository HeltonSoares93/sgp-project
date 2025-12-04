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

import br.com.sgp.ap.sgp_api.model.Usuario;
import br.com.sgp.ap.sgp_api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public Optional<Usuario> buscarUsuarioPeloId(@PathVariable("id") Long id) {
        return usuarioService.consultarUsuarioId(id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.consultarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        Optional<Usuario> usuarioExistente = usuarioService.consultarUsuarioId(id);

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);

        return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioExistente = usuarioService.consultarUsuarioId(id);
        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busca")
    public ResponseEntity<Usuario> consultarUsuarioPeloCpf(@RequestParam("cpf") String cpf) {

        Optional<Usuario> usuarioExistente = usuarioService.consultarUsuarioCpf(cpf);

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuarioExistente.get());
    }

}
