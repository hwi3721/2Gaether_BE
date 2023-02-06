package hh5.twogaether.domain.users.dto;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;
    public UserInfoResponseDto(User user) {
        this.email = user.getUsername();
        this.username = user.getNickname();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
    }
}
