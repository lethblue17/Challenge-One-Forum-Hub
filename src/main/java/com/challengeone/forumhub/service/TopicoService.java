package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Topico;
import com.challengeone.forumhub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository repository;

    public List<Topico> listarTodos() {
        return this.repository.findAll();
    }

    public Optional<Topico> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Topico criar(Topico topico) {
        topico.setDataCriacao(new Date());
        return this.repository.save(topico);
    }

    public Optional<Topico> atualizar(Long id, Topico topicoAtualizado) {
        return repository.findById(id)
                .map(topico -> {
                    topico.setTitulo(topicoAtualizado.getTitulo());
                    topico.setMensagem(topicoAtualizado.getMensagem());
                    topico.setStatus(topicoAtualizado.getStatus());
                    topico.setCurso(topicoAtualizado.getCurso());
                    return this.repository.save(topico);
                });
    }

    public boolean deletar(Long id) {
        return this.repository.findById(id)
                .map(topico -> {
                   this.repository.delete(topico);
                   return true;
                }).orElse(false);
    }
}