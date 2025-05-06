package br.com.fiap.money_flow_api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.money_flow_api.model.Token;
import br.com.fiap.money_flow_api.model.User;

@Service
public class TokenService {

    // Determinando quanto tempo o token ira expirar
    private Instant expiresAt = LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.ofHours(-3));

    // Definindo o algoritmo de criptografia 
    private Algorithm algorithm = Algorithm.HMAC256("secret");

    // Criando o token com os dados do usuário e o algoritmo de criptografia definido
    public Token createToken(User user) {
        var jwt = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().toString())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

        return new Token(jwt, user.getEmail());
    }

    public User getUserFromToken(String jwt) { 
        var jwtVerifier = JWT.require(algorithm).build().verify(jwt);
        return User.builder()
                    .id(Long.parseLong(jwtVerifier.getSubject()))  
                    .email(jwtVerifier.getClaim("email").toString())
                    .role()
                    .build();
    }
}
