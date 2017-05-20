package org.jakubd.moviedb.movie

import org.jakubd.moviedb.MockMvcSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithUserDetails

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

@WithUserDetails("user")
class DeleteMovieSpec extends MockMvcSpec {

    @Autowired
    MovieRepository movieRepository

    def 'should delete movie'() {
        given:
            def id = '123e4567-e89b-12d3-a456-426655440000'
        when:
            def response = mockMvc.perform(delete("/api/movies/$id"))
                    .andReturn().response
        then:
            response.status == 200
        and: 'should delete movie from db'
            !movieRepository.findById(id).isPresent()
    }
}
