package hh5.twogaether.domain.users.service;

import hh5.twogaether.domain.users.dto.UserInfoResponseDto;
import hh5.twogaether.domain.users.dto.UserInfoRequestDto;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;

    public void createUser(UserInfoRequestDto userInfoRequestDto) {
        User user = new User(userInfoRequestDto);
        userRepository.save(user);
    }

    // 이거 맞나? BaseEntity 되고 다시 만들게요
    public UserInfoResponseDto showMypage(UserDetailsImpl userDetails) {
        User user = userRepository.findByCreatedBy(userDetails.getUser());
        return new UserInfoResponseDto(user);
    }
    @Transactional
    public UserInfoResponseDto patchMypage(UserDetailsImpl userDetails, UserInfoRequestDto userInfoRequestDto) {
        User user = userRepository.findByCreatedBy(userDetails.getUser());
        user.UserPatch(userInfoRequestDto);
        return new UserInfoResponseDto(user);
    }

    @Transactional  // 사용자 정보 삭제 기능. 나중에 쓸 수도 있을 것 같아 만들어놓습니다
    public void deleteMypage(UserDetailsImpl userDetails) {
        User user = userRepository.findByCreatedBy(userDetails.getUser());
        if (!user.isDelete()) {
            user.UserDelete();
        } else {
            throw new IllegalArgumentException("이미 삭제된 사용자 정보입니다.");
        }
        user.UserDelete();  // 왜 두 번 해야만 할까
    }
}

