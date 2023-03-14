package hh5.twogaether.domain.mypage.service;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.dog.service.DogService;
import hh5.twogaether.domain.mypage.dto.MyPageResponseDto;
import hh5.twogaether.domain.mypage.dto.MyPageRequestDto;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DogService dogService;

    @Transactional(readOnly = true)
    public MyPageResponseDto showMyPage(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        return new MyPageResponseDto(user);
    }

    @Transactional
    public User patchMyPage(Long id, MyPageRequestDto myPageRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        if (myPageRequestDto.getNewPassword() != null) {
            String encryptPassword = passwordEncoder.encode(myPageRequestDto.getNewPassword());
            user.patchUser(new MyPageRequestDto(myPageRequestDto, encryptPassword));
            return user;
        }
        user.patchUser(myPageRequestDto);
        return user;
    }

    @Transactional  // 사용자 정보 삭제 기능. 나중에 쓸 수도 있을 것 같아 만들어놓습니다
    public void deleteMyPage(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        if (!user.isDelete()) {
            user.deleteUser();
        } else {
            throw new IllegalArgumentException("이미 삭제된 사용자 정보입니다.");
        }
        Dog dog = dogRepository.findByUserId(id);
        dogService.deleteMyDog(dog.getId(), user);
//        user.deleteUser();  // 왜 두 번 해야만 할까
    }
}

