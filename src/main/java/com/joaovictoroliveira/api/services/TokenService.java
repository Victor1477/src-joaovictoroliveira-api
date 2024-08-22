package com.joaovictoroliveira.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.jwt.algorithm.secret}")
    private String SECRET;

    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("joao-victor-oliveira-api")
                .withSubject(userDetails.getUsername())
                .withExpiresAt(generateExpirationInstant())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.require(algorithm)
                .withIssuer("joao-victor-oliveira-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationInstant() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
