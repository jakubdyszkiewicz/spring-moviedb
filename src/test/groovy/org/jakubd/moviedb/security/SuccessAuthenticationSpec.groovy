package org.jakubd.moviedb.security

import org.jakubd.moviedb.MockMvcSpec
import org.jakubd.moviedb.security.rest.JwtAuthenticationRequest
import org.springframework.http.MediaType
import spock.lang.Shared

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class SuccessAuthenticationSpec extends MockMvcSpec {

    @Shared
    static String token

    def 'should return token on authentication'() {
        when:
            def jwtRequest = new JwtAuthenticationRequest(username: 'user', password: 'user')
            def response = mockMvc.perform(post('/api/auth')
                    .content(toJson(jwtRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
            token = response.contentAsString
        then:
            response.status == 200
            !token.isEmpty()
    }

    def 'should authorize using token'() {
        when:
            def response = mockMvc.perform(get('/api/movies')
                    .header("Authorization", "Bearer $token"))
                    .andReturn().response
        then:
            response.status == 200
    }
}
