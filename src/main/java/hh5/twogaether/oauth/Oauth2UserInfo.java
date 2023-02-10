package hh5.twogaether.oauth;

import java.util.Map;

public interface Oauth2UserInfo {
    Map<String, Object> getAttributes();
    String getProvider();
    String getEmail();
    String getNickname();
}
