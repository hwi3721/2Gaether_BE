package hh5.twogaether.domain.mypage.controller;

import hh5.twogaether.domain.mypage.dto.MyPageRequestDto;
import hh5.twogaether.domain.mypage.dto.MyPageResponseDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.mypage.service.MyPageService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @PatchMapping("")
    public ResponseEntity<MyPageResponseDto> patchMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @RequestBody MyPageRequestDto myPageRequestDto) {
        User user = myPageService.patchMyPage(userDetails.getUser().getId(), myPageRequestDto);
        return new ResponseEntity<>(new MyPageResponseDto(user), CREATED);
    }

    @GetMapping(" ")
    public ResponseEntity<MyPageResponseDto> showMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        myPageService.showMyPage(userDetails.getUser().getId());
        return new ResponseEntity<>(new MyPageResponseDto(userDetails.getUser()), OK);
    }
}
