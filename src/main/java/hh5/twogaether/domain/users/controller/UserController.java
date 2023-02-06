package hh5.twogaether.domain.users.controller;

import hh5.twogaether.domain.users.dto.UserSignupRequestDto;
import hh5.twogaether.domain.users.service.UserService;
import hh5.twogaether.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public String healthCheck() {
        return "hello";
    }

//    @PostMapping("/users/signup")
//    public String createUser(UserSignupRequestDto signupRequestDto) {
//        userService.checkEmailDuplication(signupRequestDto.getEmail());
//    }
}
