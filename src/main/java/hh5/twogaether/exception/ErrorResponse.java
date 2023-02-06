package hh5.twogaether.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ErrorResponse {

        private ErrorCode errorCode;
//        private String errorMessage;

        private ErrorResponse(ErrorCode errorCode) {
            this.errorCode = errorCode;

        }

        public static ErrorResponse of(ErrorCode errorCode) {
            return new ErrorResponse(errorCode);
        }

//        public ErrorCode getErrorCode() {
//            return errorCode;
//        }

//        public String getErrorMessage() {
//            return errorMessage;
//        }
    }
