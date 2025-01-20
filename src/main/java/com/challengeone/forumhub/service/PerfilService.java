package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Perfil;
import com.challengeone.forumhub.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository repository;

    public List<Perfil> listarTodos() {
        return this.repository.findAll();
    }

    public Optional<Perfil> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Perfil criar(Perfil perfil) {
        return this.repository.save(perfil);
    }

    public Optional<Perfil> atualizar(Long id, Perfil perfilAtualizado) {
        return repository.findById(id)
                .map(perfil -> {
                    perfil.setNome(perfilAtualizado.getNome());
                    return this.repository.save(perfil);
                });
    }

    public boolean deletar(Long id) {
        return this.repository.findById(id)
                .map(perfil -> {
                   this.repository.delete(perfil);
                   return true;
                }).orElse(false);
    }
}