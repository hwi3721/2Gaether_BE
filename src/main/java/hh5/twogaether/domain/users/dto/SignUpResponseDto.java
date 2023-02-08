package hh5.twogaether.domain.users.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private Integer status;
    private String message;

    public SignUpResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
