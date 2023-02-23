package hh5.twogaether.exception.message;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    EXISTED_EMAIL("이미 존재하는 이메일입니다."),
    EXISTED_USERNAME("이미 존재하는 닉네임입니다."),
    INCORRECT_SIGN_IN_TRY("아이디/비밀번호가 올바르지 않습니다."),
    NOT_EXISTED_ID("아이디가 존재하지 않습니다."),
    INVALID_EMAIL_ACCOUNT("인증되지 않은 계정입니다.");

    private String description;

    ExceptionMessage(String description) {
        this.description = description;
    }
}
