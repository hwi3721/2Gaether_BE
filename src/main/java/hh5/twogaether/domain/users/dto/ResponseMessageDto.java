package hh5.twogaether.domain.users.dto;

import lombok.Getter;

@Getter
public class ResponseMessageDto {

    private Integer status;
    private String message;

    public ResponseMessageDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
