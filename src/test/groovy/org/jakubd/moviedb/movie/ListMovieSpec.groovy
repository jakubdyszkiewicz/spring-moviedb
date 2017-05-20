package org.jakubd.moviedb.movie

import groovy.json.JsonSlurper
import org.jakubd.moviedb.MockMvcSpec
import org.springframework.security.test.context.support.WithUserDetails

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WithUserDetails("user")
class ListMovieSpec extends MockMvcSpec {

    def 'should list all entries'() {
        when:
            def response = mockMvc.perform(get('/api/movies'))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then:
            response.status == 200
            content.size() == 3
            content.any {
                it.id != null &&
                it.title == 'Intouchables' &&
                it.description == 'An unlikely friendship develops between a wealthy quadriplegic (François Cluzet) and his caretaker (Omar Sy), just released from prison.' &&
                it.watched == false
            }
    }
}
