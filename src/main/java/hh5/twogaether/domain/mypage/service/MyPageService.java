package hh5.twogaether.domain.mypage.service;

import hh5.twogaether.domain.mypage.dto.MyPageResponseDto;
import hh5.twogaether.domain.mypage.dto.MyPageRequestDto;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    public User createUserInfo(MyPageRequestDto myPageRequestDto) {
        User user = new User(myPageRequestDto);
        userRepository.save(user);
        return user;
    }

    // 이거 맞나? BaseEntity 되고 다시 만들게요
    public MyPageResponseDto showMypage(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        return new MyPageResponseDto(user);
    }

    @Transactional
    public User patchMypage(Long id, MyPageRequestDto myPageRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        user.UserPatch(myPageRequestDto);
        return user;
    }

    @Transactional  // 사용자 정보 삭제 기능. 나중에 쓸 수도 있을 것 같아 만들어놓습니다
    public void deleteMypage(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        if (!user.isDelete()) {
            user.UserDelete();
        } else {
            throw new IllegalArgumentException("이미 삭제된 사용자 정보입니다.");
        }
        user.UserDelete();  // 왜 두 번 해야만 할까
    }
}

