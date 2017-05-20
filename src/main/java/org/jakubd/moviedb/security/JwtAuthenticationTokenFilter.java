package org.jakubd.moviedb.security;

import org.jakubd.moviedb.security.exception.JwtMissingException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String HEADER_VALUE_PREFIX = "Bearer ";
    private static final String AUTH_ENDPOINT = "/api/auth";

    private final UserDetailsService userDetailsService;
    private final JwtParser jwtParser;

    JwtAuthenticationTokenFilter(UserDetailsService userDetailsService, JwtParser jwtParser) {
        super(request -> !request.getRequestURI().startsWith(AUTH_ENDPOINT));
        this.userDetailsService = userDetailsService;
        this.jwtParser = jwtParser;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return SecurityContextHolder.getContext().getAuthentication();
        }

        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(HEADER_VALUE_PREFIX)) {
            throw new JwtMissingException();
        }

        String token = header.substring(HEADER_VALUE_PREFIX.length());

        String username = jwtParser.parse(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
    }

    /**
     * After success, the request should be continued normally
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
