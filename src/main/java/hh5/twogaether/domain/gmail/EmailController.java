package hh5.twogaether.domain.gmail;

import hh5.twogaether.security.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailServiceImpl emailServiceImpl;
    @GetMapping("/email/{emailCode}/{userId}")
    public ResponseEntity<EmailCheckSuccessResponseDto> emailConfirm(@PathVariable String emailCode,
                                                                     @PathVariable Long userId)  throws Exception {
        emailServiceImpl.emailLinkCheck(emailCode, userId);

        EmailCheckSuccessResponseDto emailCheckSuccessResponseDto = new EmailCheckSuccessResponseDto(200, ResponseMessage.EMAIL_SUCCESS_CHECK_LINK);
        return new ResponseEntity<>(emailCheckSuccessResponseDto, HttpStatus.OK);
    }
}
