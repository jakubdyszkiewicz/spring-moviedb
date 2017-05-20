package org.jakubd.moviedb.movie

import groovy.json.JsonSlurper
import org.jakubd.moviedb.MockMvcSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithUserDetails
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@WithUserDetails("user")
class UpdateMovieSpec extends MockMvcSpec {

    @Autowired
    MovieRepository movieRepository

    def 'should update movie'() {
        given:
            def id = '123e4567-e89b-12d3-a456-426655440000'
            def movie = new MovieDto(id: id, title: 'A', description: 'B', watched: true)
        when:
            def response = mockMvc.perform(put("/api/movies/$id")
                    .content(toJson(movie))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then: 'should return updated movie'
            response.status == 200
            content.title == movie.title
            content.description == movie.description
            content.watched == movie.watched
        and: 'should persist movie'
            def saved = movieRepository.findById(id)
                    .orElseGet { assert false }
            saved.id == id
            saved.title == movie.title
            saved.description == movie.description
            saved.watched == movie.watched
    }

    @Unroll
    def 'should validate invalid title'(String title) {
        given:
            def movie = new MovieDto(title: title, description: 'B', watched: true)
        when:
            def response = mockMvc.perform(put('/api/movies/123e4567-e89b-12d3-a456-426655440000')
                    .content(toJson(movie))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
        then:
            response.status == 400
        where:
            title     | _
            null      | _
            ''        | _
            'x' * 501 | _
    }

    @Unroll
    def 'should validate invalid description'(String description) {
        given:
            def movie = new MovieDto(title: 'A', description: description, watched: true)
        when:
            def response = mockMvc.perform(put('/api/movies/123e4567-e89b-12d3-a456-426655440000')
                    .content(toJson(movie))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
        then:
            response.status == 400
        where:
            description | _
            null        | _
            ''          | _
            'x' * 1001  | _
    }

    def 'should return not found on invalid id'() {
        given:
            def movie = new MovieDto(title: 'A', description: 'B', watched: true)
        when:
            def response = mockMvc.perform(put('/api/movies/321')
                    .content(toJson(movie))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
        then:
            response.status == 404
    }
}
