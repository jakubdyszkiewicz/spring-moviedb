package org.jakubd.moviedb.security

import org.jakubd.moviedb.MockMvcSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class ExpiredTokenSpec extends MockMvcSpec {

    final String token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNDk1MjgyNTMxfQ.3nIh_7RpieQlTlfOLsB09z2eOWBeiW1hk5GQfs8o1oaQTeyRHUeZ5rbody6fiFG2m6L-fwceMNHwOwo95rYDhw'

    def 'should authorize using token'() {
        when:
            def response = mockMvc.perform(get('/api/movies')
                    .header("Authorization", "Bearer $token"))
                    .andReturn().response
        then:
            response.status == 401
    }
}

