package hh5.twogaether.oauth;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoUserInfo implements Oauth2UserInfo{

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        log.info("[KakaoUserInfo] providerId = {}",String.valueOf(attributes.get("id")));
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        log.info("[KakaoUserInfo] email = {}",(String) getKakaoAccount().get("email"));
        return (String) getKakaoAccount().get("email");
    }

    @Override
    public String getNickname() {
        log.info("[KakaoUserInfo] nickname = {}",(String) getProfile().get("nickname"));
        return (String) getProfile().get("nickname");
    }

    public Map<String, Object> getKakaoAccount(){
        return(Map<String, Object>) attributes.get("kakao_account");
    }

    public Map<String, Object> getProfile(){
        return (Map<String, Object>) getKakaoAccount().get("profile");
    }
}
