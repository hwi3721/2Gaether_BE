package hh5.twogaether.domain.gmail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GmailController {
    private GmailServiceImpl gmailServiceImpl;
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {
        return gmailServiceImpl.sendSimpleMessage(email);
    }
}
