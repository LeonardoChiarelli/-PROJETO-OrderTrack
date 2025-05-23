package br.com.OrderTrack.Order.general.security;

import br.com.OrderTrack.Order.domain.model.User;
import br.com.OrderTrack.Order.general.infra.exception.ValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String ISSUE = "ORDER TRACK API";

    @Value("${app.security.token.secret}")
    private String tokenSecret;

    public String generateToken(@NotNull User user) {
        try{
            var algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer(ISSUE)
                    .withSubject(user.getEmail())
                    .withClaim("name", user.getName())
                    .withExpiresAt(dateOfExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new ValidationException("Error to generate the JWT Token: "  + exception.getMessage());
        }
    }

    public String getSubject(@NotNull String token) {
        try{
            var algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUE)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new ValidationException("Invalid JWT Token or It's expired: " + exception.getMessage());
        }
    }

    private Instant dateOfExpiration() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
