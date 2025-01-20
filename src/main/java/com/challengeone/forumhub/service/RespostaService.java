package com.challengeone.forumhub.service;

import com.challengeone.forumhub.model.Resposta;
import com.challengeone.forumhub.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    private RespostaRepository repository;

    public List<Resposta> listarTodos() {
        return this.repository.findAll();
    }

    public Optional<Resposta> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    public Resposta criar(Resposta resposta) {
        return this.repository.save(resposta);
    }

    public Optional<Resposta> atualizar(Long id, Resposta respostaAtualizada) {
        return repository.findById(id)
                .map(resposta -> {
                    resposta.setMensagem(respostaAtualizada.getMensagem());
                    resposta.setSolucao(respostaAtualizada.getSolucao());
                    return this.repository.save(resposta);
                });
    }

    public boolean deletar(Long id) {
        return this.repository.findById(id)
                .map(resposta -> {
                   this.repository.delete(resposta);
                   return true;
                }).orElse(false);
    }
}