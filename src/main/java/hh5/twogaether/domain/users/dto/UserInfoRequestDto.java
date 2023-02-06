package hh5.twogaether.domain.users.dto;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserInfoRequestDto {
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;

}
