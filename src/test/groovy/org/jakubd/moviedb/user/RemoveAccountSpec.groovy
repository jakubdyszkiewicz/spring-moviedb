package org.jakubd.moviedb.user

import org.jakubd.moviedb.MockMvcSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithUserDetails

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

@WithUserDetails("user")
class RemoveAccountSpec extends MockMvcSpec {

    @Autowired
    UserRepository userRepository

    def 'should remove own account'() {
        when:
            def response = mockMvc.perform(delete('/api/me'))
                    .andReturn().response
        then:
            response.status == 200
            !userRepository.findByLogin("user").isPresent()
    }
}
