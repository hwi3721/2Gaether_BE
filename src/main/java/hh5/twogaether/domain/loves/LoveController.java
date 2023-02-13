package hh5.twogaether.domain.loves;

import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loves")
public class LoveController {
    private final LoveService loveService;
    @PostMapping("/{lovedId}")
    public ResponseEntity<Void> love(@PathVariable Long lovedId,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        loveService.love(lovedId, userDetails.getUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
