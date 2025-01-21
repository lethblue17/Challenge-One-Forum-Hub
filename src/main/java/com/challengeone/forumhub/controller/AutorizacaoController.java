package com.challengeone.forumhub.controller;

import com.challengeone.forumhub.config.security.TokenService;
import com.challengeone.forumhub.model.Autorizacao;
import com.challengeone.forumhub.model.LoginResponse;
import com.challengeone.forumhub.model.Registro;
import com.challengeone.forumhub.model.Usuario;
import com.challengeone.forumhub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutorizacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/entrar")
    public ResponseEntity entrar(@RequestBody @Valid Autorizacao dados) {
        var senhaUsuario = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        var auth = this.authenticationManager.authenticate(senhaUsuario);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping(value = "/registrar")
    public ResponseEntity registrar(@RequestBody @Valid Registro dados) {

        if (this.usuarioRepository.findByEmail(dados.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.getSenha());

        Usuario usuario = new Usuario();
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setNome(dados.getNome());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
