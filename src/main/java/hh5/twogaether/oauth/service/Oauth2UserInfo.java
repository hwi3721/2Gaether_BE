package hh5.twogaether.oauth.service;

import java.util.Map;

public interface Oauth2UserInfo {
    Map<String, Object> getAttributes();
    String getProvider();
    String getEmail();
    String getNickname();
}
