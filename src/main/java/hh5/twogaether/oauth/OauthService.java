package hh5.twogaether.oauth;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OauthService {

    private final InMemoryClientRegistrationRepository inMemoryRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    /**
     * @InMemoryRepository 는 application-oauth properties 정보를 담고 있음
     * @getToken() 넘겨받은 code 로 OAuth 서버에 Token 요청
     * @getUserProjile 첫 로그인 시 회원가입
     * TODO 유저 인증 후 Jwt AccessToken, Refresh Token 생성
     */
    @Transactional
    public String login(String providerName, String code) throws IllegalAccessException {

        ClientRegistration provider = inMemoryRepository.findByRegistrationId(providerName);
        log.info("[Service] RedirectUri = {}",provider.getRedirectUri());
        log.info("[Service] ClientId = {}",provider.getClientId());
        log.info("[Service] ClientSecret = {}",provider.getClientSecret());
        log.info("[Service] ClientSecret = {}",provider.getProviderDetails().getTokenUri());
        // authorization code 로 액세스 토큰 요청해서 받아옴
        OauthTokenResponseDto tokenResponse = getToken(code, provider);
        log.info("[Service] tokenResponse = {}", tokenResponse.toString());

        String email = getUerProfile(providerName, tokenResponse, provider);
        log.info("email = {}", email);

        // access, refresh 토큰 만들고

        return email;

    }

    // 1. authorization code 로 토큰 요청
    private OauthTokenResponseDto getToken(String code, ClientRegistration provider) {
        return WebClient.create()
                .post()
                .uri(provider.getProviderDetails().getTokenUri())
                .headers(header -> {  // HTTP Header 생성
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(requestToken(code, provider))
                .retrieve()
                .bodyToMono(OauthTokenResponseDto.class)
                .block();
    }

    // HTTP Body 생성
    private MultiValueMap<String, String> requestToken(String code, ClientRegistration provider) {
        LinkedMultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("client_id", provider.getClientId());
        return formData;
    }

    // 2. 받아온 "액세스 토큰"으로 카카오 API 호출 -> 카카오 사용자 정보 가져오기
    private String getUerProfile(String providerName, OauthTokenResponseDto tokenResponse,
                                 ClientRegistration provider) throws IllegalAccessException {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        Oauth2UserInfo oauth2UserInfo = null;
        if (providerName.equals("kakao")) {
            oauth2UserInfo = new KakaoUserInfo(userAttributes);
        } else {
            throw new IllegalAccessException("허용되지 않은 접근입니다.");
        }

        String provide = oauth2UserInfo.getProvider();
//        String providerId = oauth2UserInfo.getProviderId();
        String nickname = oauth2UserInfo.getNickname();
        String email = oauth2UserInfo.getEmail();

        Optional<User> foundUser = userRepository.findByUsername(email);

        if (foundUser.isEmpty()) {
            User user = new User(nickname, email, provide);
            userRepository.save(user);
        }
        return email;
    }

    private Map<String, Object> getUserAttributes(ClientRegistration provider,
                                                  OauthTokenResponseDto tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }

}
