package hh5.twogaether.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATED_EMAIL("이미 가입한 이메일입니다."),

    // 404 Not Found
    NOT_FOUND_PRODUCT("해당 관심상품 아이디가 존재하지 않습니다."),
    NOT_FOUND_FOLDER("해당 폴더 아이디가 존재하지 않습니다.");


    private final String errorCode;

    ErrorCode(String errorMessage) {
        this.errorCode = getErrorCode();
    }
}