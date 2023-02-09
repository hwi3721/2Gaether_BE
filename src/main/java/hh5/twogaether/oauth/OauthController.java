package hh5.twogaether.oauth;

import hh5.twogaether.domain.users.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OauthController {

    private final OauthService oauthService;

    /**
     * OAuth 로그인 시 인증 코드를 넘겨받은 후 첫 로그인 시 회원가입
     */
    // redirect url 과 authorization code 를 받아온다.
    @PostMapping("/login/oauth/{providerName}")
    public String login(@PathVariable String providerName,
                                                  @RequestBody KakaoLoginRequestDto kakaoRequestDto) throws IllegalAccessException {
        log.info("[Controller] providerName = {}, code = {}", providerName, kakaoRequestDto.getCode());
        return oauthService.login(providerName, kakaoRequestDto.getCode());
    }
}
