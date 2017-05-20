package org.jakubd.moviedb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.jakubd.moviedb.security.exception.JwtParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class JwtParser {

    private final String signingKey;

    JwtParser(@Value("${jwt.signingKey}") String signingKey) {
        this.signingKey = signingKey;
    }

    String parse(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token)
                    .getBody();
            return body.getSubject();
        } catch (Exception e) {
            throw new JwtParseException(e);
        }
    }
}
