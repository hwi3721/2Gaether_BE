package hh5.twogaether.domain.gmail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class GmailController {
    private final GmailServiceImpl gmailServiceImpl;
    @PostMapping("/emailConfirm")
//    @ResponseBody
    public ResponseEntity<Void> emailConfirm(@RequestBody GmailRequestDto email) throws Exception {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        gmailServiceImpl.sendSimpleMessage(email);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/validatEmail")
    public boolean validateEmail(@RequestBody GmailRequestDto email, String code) throws Exception {
        boolean valid = false;
        String s = gmailServiceImpl.sendSimpleMessage(email);
        if (Objects.equals(code, s)) {
            valid = true;
        }
        return valid;
    }
}
