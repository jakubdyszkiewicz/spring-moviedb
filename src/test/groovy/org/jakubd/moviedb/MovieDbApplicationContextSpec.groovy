package org.jakubd.moviedb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [MovieDbApplication])
class MovieDbApplicationContextSpec extends Specification {

    @Autowired
    ApplicationContext context

    def 'should load context'() {
        expect:
            context != null
    }
}
