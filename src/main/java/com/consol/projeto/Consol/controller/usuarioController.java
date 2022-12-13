package com.consol.projeto.Consol.controller;

import com.consol.projeto.Consol.model.Usuario;
import com.consol.projeto.Consol.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080/", maxAge = 3600)
@RequestMapping("/usuarios")
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario){

        Usuario usuarioAdd = usuarioService.add(usuario);
        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioAdd);
    }

    @GetMapping
    public List<Usuario> listaTodosUsuarios(){
        return  usuarioService.listaTodosUsuarios();
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterUsuario(@Valid @PathVariable Long id, @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.alter(id, usuario));
    }

    @GetMapping("/{id}")
    public Usuario listaUsuarioId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.validaUsuario(id)).getBody();
    }



}
