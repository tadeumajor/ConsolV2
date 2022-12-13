package com.consol.projeto.Consol.service;

import com.consol.projeto.Consol.model.Usuario;
import com.consol.projeto.Consol.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        validaUsuario(id);
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listaTodosUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario alter(long id, Usuario usuario){
        Usuario usuarioAlter = validaUsuario(id);
        BeanUtils.copyProperties(usuario, usuarioAlter);
        return usuarioRepository.save(usuarioAlter);

    }

    public Usuario getUsuarioById(long id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario validaUsuario(long id) {
        Optional<Usuario> usuarioBusca = usuarioRepository.findById(id);
        if (usuarioBusca.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return usuarioBusca.get();
    }
}

