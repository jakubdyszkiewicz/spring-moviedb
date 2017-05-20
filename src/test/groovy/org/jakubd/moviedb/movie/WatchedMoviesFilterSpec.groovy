package org.jakubd.moviedb.movie

import groovy.json.JsonSlurper
import org.jakubd.moviedb.MockMvcSpec
import org.springframework.security.test.context.support.WithUserDetails

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WithUserDetails("user")
class WatchedMoviesFilterSpec extends MockMvcSpec {

    def 'should filter watched movies'() {
        when:
            def response = mockMvc.perform(get('/api/movies')
                    .param('watched', 'true'))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then:
            !content.isEmpty()
            content.every { it.watched == true }
    }

    def 'should filter unwatched movies'() {
        when:
            def response = mockMvc.perform(get('/api/movies')
                    .param('watched', 'false'))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then:
            !content.isEmpty()
            content.every { it.watched == false }
    }
}
