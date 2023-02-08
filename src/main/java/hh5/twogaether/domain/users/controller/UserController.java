package hh5.twogaether.domain.users.controller;

import hh5.twogaether.domain.users.dto.LoginRequestDto;
import hh5.twogaether.domain.users.dto.LoginResponseDto;
import hh5.twogaether.domain.users.dto.SignUpRequestDto;
import hh5.twogaether.domain.users.dto.SignUpResponseDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.service.UserService;
import hh5.twogaether.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static hh5.twogaether.security.jwt.JwtUtil.AUTHORIZATION_HEADER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public String healthCheck() {
        return "hello";
    }

    //회원 가입
    @PostMapping("/users/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signupRequestDto) {
        userService.checkEmailDuplication(signupRequestDto.getEmail());
        userService.createUser(signupRequestDto);

        return new ResponseEntity<>(new SignUpResponseDto(CREATED.value(), "회원가입 완료"), CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto,
                                                      HttpServletResponse response) {

        User user = userService.login(loginRequestDto);
        response.addHeader(AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        return new ResponseEntity<>(new LoginResponseDto(OK.value(), "로그인 완료", user.getUsername()),OK);
    }
}
