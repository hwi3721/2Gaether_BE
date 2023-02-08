package hh5.twogaether.domain.users.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Integer status;
    private String message;
    private String username;

    public LoginResponseDto(Integer status, String message, String username) {
        this.status = status;
        this.message = message;
        this.username = username;
    }
}
