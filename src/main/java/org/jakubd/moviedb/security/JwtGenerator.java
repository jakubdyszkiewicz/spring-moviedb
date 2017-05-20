package org.jakubd.moviedb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtGenerator {

    private final String signingKey;
    private final int expirationInSeconds;

    public JwtGenerator(@Value("${jwt.signingKey}") String signingKey, @Value("${jwt.expirationInSeconds}") int expirationInSeconds) {
        this.signingKey = signingKey;
        this.expirationInSeconds = expirationInSeconds;
    }


    public String generate(UserDetails user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        Date expirationDate = Date.from(ZonedDateTime.now().plusSeconds(expirationInSeconds).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }
}
