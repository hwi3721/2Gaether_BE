//package hh5.twogaether.domain.users.service;
//
//import hh5.twogaether.domain.users.dto.UserInfoResponseDto;
//import hh5.twogaether.domain.users.dto.UserInfoRequestDto;
//import hh5.twogaether.domain.users.repository.UserRepository;
//import hh5.twogaether.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import hh5.twogaether.domain.users.entity.User;
//
//@Service
//@RequiredArgsConstructor
//public class UserInfoService {
//    private final UserRepository userRepository;
//
//    public void createUser(UserInfoRequestDto userInfoRequestDto) {
//        User user = new User(userInfoRequestDto);
//        userRepository.save(user);
//    }
//
//    // 이거 맞나? BaseEntity 되고 다시 만들게요
//    public UserInfoResponseDto showMypage(UserDetailsImpl userDetails) {
//        User user = userRepository.findByCreatedBy(userDetails.getUser());
//        return new UserInfoResponseDto(user);
//    }
//}
