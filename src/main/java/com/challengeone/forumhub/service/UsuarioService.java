package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Usuario;
import com.challengeone.forumhub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return this.repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Usuario criar(Usuario usuario) {
        return this.repository.save(usuario);
    }

    public Optional<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {
        return repository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    return this.repository.save(usuario);
                });
    }

    public boolean deletar(Long id) {
        return this.repository.findById(id)
                .map(usuario -> {
                   this.repository.delete(usuario);
                   return true;
                }).orElse(false);
    }
}
