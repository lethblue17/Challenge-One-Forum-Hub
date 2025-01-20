package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Curso;
import com.challengeone.forumhub.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private CursoRepository repository;

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Curso criar(Curso curso) {
        return repository.save(curso);
    }

    public Optional<Curso> atualizar(Long id, Curso cursoAtualizado) {
        return repository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setCategoria(cursoAtualizado.getCategoria());
                    return repository.save(curso);
                });
    }

    public boolean deletar(Long id) {
        return repository.findById(id)
                .map(curso -> {
                    repository.delete(curso);
                    return true;
                }).orElse(false);
    }
}
