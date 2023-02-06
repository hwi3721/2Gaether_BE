package hh5.twogaether.domain.users.controller;

import hh5.twogaether.domain.users.dto.UserInfoRequestDto;
import hh5.twogaether.domain.users.dto.UserInfoResponseDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.service.UserInfoService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/mypage")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping("/")
    public ResponseEntity<UserInfoResponseDto> createMypage(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        userInfoService.createUserInfo(userInfoRequestDto);
        return new ResponseEntity<>(new UserInfoResponseDto(userInfoRequestDto), CREATED);
    }

    @PatchMapping("/")
    public ResponseEntity<UserInfoResponseDto> patchMypage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           UserInfoRequestDto userInfoRequestDto) {
        userInfoService.patchMypage(userDetails.getUser().getUserId(), userInfoRequestDto);
        return new ResponseEntity<>(new UserInfoResponseDto(userInfoRequestDto), CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<UserInfoResponseDto> showMypage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userInfoService.showMypage(userDetails.getUser().getUserId());
        return new ResponseEntity<>(new UserInfoResponseDto(userDetails.getUser()), OK);
    }
}
