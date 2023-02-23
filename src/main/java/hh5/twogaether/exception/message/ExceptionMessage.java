package hh5.twogaether.exception.message;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    EXISTED_EMAIL("이미 존재하는 이메일입니다."),
    EXISTED_USERNAME("이미 존재하는 닉네임입니다."),
    INCORRECT_SIGN_IN_TRY("아이디/비밀번호가 올바르지 않습니다."),
    NOT_EXISTED_ID("아이디가 존재하지 않습니다."),
    ALREADY_DELETED_ID("이미 삭제된 아이디입니다."),
    ALREADY_EXISTED_ROOM("이미 존재하는 방입니다.");

    private String description;

    ExceptionMessage(String description) {
        this.description = description;
    }
}
