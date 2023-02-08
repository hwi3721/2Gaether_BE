package hh5.twogaether.domain.users.service;

import hh5.twogaether.domain.users.dto.LoginRequestDto;
import hh5.twogaether.domain.users.dto.SignUpRequestDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hh5.twogaether.exception.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(SignUpRequestDto signupRequestDto) {
        String encryptPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        SignUpRequestDto encryptSignUpRequestDto = new SignUpRequestDto(signupRequestDto, encryptPassword);
        User user = new User(encryptSignUpRequestDto);
        userRepository.save(user);
    }

    public User login(LoginRequestDto loginRequestDto) {
        User users = userRepository.findByUsername(loginRequestDto.getEmail())
                .orElseThrow(() -> new BadCredentialsException(INCORRECT_SIGN_IN_TRY.getDescription()));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), users.getPassword())) {
            throw new BadCredentialsException(INCORRECT_SIGN_IN_TRY.getDescription());
        }

        return users;
    }

    public void checkEmailDuplication(String email) {
        Optional<User> foundUser = userRepository.findByUsername(email);

        if (foundUser.isPresent()) {
            throw new IllegalArgumentException(EXISTED_EMAIL.getDescription());
        }
    }
}
