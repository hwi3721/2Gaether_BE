package hh5.twogaether.domain.match.controller;

import hh5.twogaether.domain.chat.service.ChatRoomService;
import hh5.twogaether.domain.loves.service.LoveService;
import hh5.twogaether.domain.match.dto.MatchDogResponseDto;
import hh5.twogaether.domain.match.service.MatchService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final LoveService loveService;

    //매칭 상대 보기
    @GetMapping
    public ResponseEntity<MatchDogResponseDto> showMatches(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(matchService.getMatches(userDetails.getUser().getId()), OK);
    }

    //좋아요
    @PostMapping("/love/{dogId}")
    public ResponseEntity<MatchDogResponseDto> loveAndShowNext(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable Long dogId) {
        //좋아요 로직
        loveService.loveUser(dogId, userDetails.getUser());
        matchService.passUser(dogId, userDetails.getUser().getId());
        return new ResponseEntity<>(matchService.getMatches(userDetails.getUser().getId()), CREATED);
    }

    //싫어요
    @PostMapping("/reject/{dogId}")
    public ResponseEntity<MatchDogResponseDto> rejectAndShowNext(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long dogId) {
        matchService.passUser(dogId,userDetails.getUser().getId());
        return new ResponseEntity<>(matchService.getMatches(userDetails.getUser().getId()), CREATED);
    }
}
