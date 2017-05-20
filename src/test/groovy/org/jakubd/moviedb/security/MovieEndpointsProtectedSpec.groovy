package org.jakubd.moviedb.security

import org.jakubd.moviedb.MockMvcSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class MovieEndpointsProtectedSpec extends MockMvcSpec {

    def 'should return 401 when user is not authenticated'() {
        when:
            def response = mockMvc.perform(get('/api/movies'))
                    .andReturn().response
        then:
            response.status == 401
    }
}
