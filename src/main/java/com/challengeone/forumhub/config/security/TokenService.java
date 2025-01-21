package com.challengeone.forumhub.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.challengeone.forumhub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("forum-hub")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("forum-hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
