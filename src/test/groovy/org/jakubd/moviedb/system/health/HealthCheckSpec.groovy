package org.jakubd.moviedb.system.health

import groovy.json.JsonSlurper
import org.jakubd.moviedb.MockMvcSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class HealthCheckSpec extends MockMvcSpec {

    def 'should check health'() {
        when:
            def response = mockMvc.perform(get('/api/health'))
                    .andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then:
            response.status == 200
            content.status == "ok"
    }
}
