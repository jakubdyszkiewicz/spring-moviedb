package org.jakubd.moviedb.movie

import groovy.json.JsonSlurper
import org.jakubd.moviedb.MockMvcSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class AddMovieSpec extends MockMvcSpec {

    @Autowired
    MovieRepository movieRepository

    def 'should add new movie'() {
        given:
            def movie = new MovieDto(title: 'AV', description: 'BA', watched: true)
        when:
            def response = mockMvc.perform(post('/api/movies')
                    .content(toJson(movie))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then: 'should return created movie'
            response.status == 201
            content.title == movie.title
            content.description == movie.description
            content.watched == movie.watched
        and: 'should persist movie'
            def saved = movieRepository.findById(content.id)
                    .orElseGet { assert false }
            saved.id != null
            saved.title == movie.title
            saved.description == movie.description
            saved.watched == movie.watched
    }

    @Unroll
    def 'should validate invalid title'(String title) {
        given:
            def movie = new MovieDto(title: title, description: 'B', watched: true)
        when:
            def response = mockMvc.perform(post('/api/movies')
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
            def response = mockMvc.perform(post('/api/movies')
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
}
