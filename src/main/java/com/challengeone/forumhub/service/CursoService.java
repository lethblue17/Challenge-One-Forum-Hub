package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Curso;
import com.challengeone.forumhub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> listarTodos() {
        return this.repository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Curso criar(Curso curso) {
        return this.repository.save(curso);
    }

    public Optional<Curso> atualizar(Long id, Curso cursoAtualizado) {
        return this.repository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setCategoria(cursoAtualizado.getCategoria());
                    return this.repository.save(curso);
                });
    }

    public boolean deletar(Long id) {
        return this.repository.findById(id)
                .map(curso -> {
                    this.repository.delete(curso);
                    return true;
                }).orElse(false);
    }
}
