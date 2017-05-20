package org.jakubd.moviedb.security.rest;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}
