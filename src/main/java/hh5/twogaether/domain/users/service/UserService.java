package hh5.twogaether.domain.users.service;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.exception.message.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hh5.twogaether.exception.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void checkEmailDuplication(String email) {
//        String findEmail = userRepository.findByUsername(email);

//        if (isExisted(findEmail)) {
//            throw new IllegalArgumentException(EXISTED_EMAIL.getDescription());
//        }
    }

//    @Transactional
//    public String

    private boolean isExisted(String field) {
        return field != null;
    }
}
