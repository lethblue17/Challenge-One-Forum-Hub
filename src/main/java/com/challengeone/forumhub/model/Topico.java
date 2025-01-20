package com.challengeone.forumhub.model;

import java.util.Date;
import java.util.List;

public class Topico {

    private Long id;
    private String titulo;
    private String mensagem;
    private Date dataCriacao;
    private String status;
    private Usuario autor;
    private Curso curso;
    private List<Resposta> respostas;

}
