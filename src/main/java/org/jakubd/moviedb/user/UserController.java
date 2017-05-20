package org.jakubd.moviedb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @DeleteMapping("/api/me")
    public void removeAccount() {
        userService.removeAccount();
    }
}
