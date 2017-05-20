package org.jakubd.moviedb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void removeAccount() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.deleteByLogin(login);
    }
}
