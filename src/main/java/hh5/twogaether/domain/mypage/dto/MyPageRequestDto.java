package hh5.twogaether.domain.mypage.dto;

import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class MyPageRequestDto {
    private String email;
    private String username;
    private String password;
    private Double latitude;
    private Double longitude;
    private String detailAddress;

}
