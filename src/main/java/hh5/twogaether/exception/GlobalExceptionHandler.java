package hh5.twogaether.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleDuplicatedEmailException(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);
        final ErrorCode errorCode = ErrorCode.valueOf(e.getMessage());
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
