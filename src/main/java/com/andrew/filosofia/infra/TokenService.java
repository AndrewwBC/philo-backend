package com.andrew.filosofia.infra;

import com.andrew.filosofia.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(this.secret);

        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getId())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(24)))
                    .sign(algorithm);
        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }

    }
    public String validateTokenAndGetUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.secret);

        try {
            String userId = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return userId;
        } catch (JwtValidationException exception) {
            throw new JWTVerificationException(exception.getMessage());
        }
    }


}
