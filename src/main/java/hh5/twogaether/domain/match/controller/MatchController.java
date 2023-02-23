package hh5.twogaether.domain.match.controller;

import hh5.twogaether.domain.loves.service.LoveService;
import hh5.twogaether.domain.match.dto.MatchDogResponseDto;
import hh5.twogaether.domain.match.service.MatchService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final LoveService loveService;

    //매칭 상대 보기
    @GetMapping
    public MatchDogResponseDto showMatches(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return matchService.getMatches(userDetails.getUser().getId());
    }

    //좋아요
    @GetMapping("/love/{dogId}")
    public MatchDogResponseDto loveAndShowNext(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable Long dogId) {
        //좋아요 로직
        loveService.loveUser(dogId, userDetails.getUser().getId());
        matchService.passUser(dogId, userDetails.getUser().getId());
        return matchService.getMatches(userDetails.getUser().getId());
    }

    //싫어요
    @GetMapping("/reject/{dogId}")
    public MatchDogResponseDto rejectAndShowNext(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long dogId) {
        matchService.passUser(dogId,userDetails.getUser().getId());
        return matchService.getMatches(userDetails.getUser().getId());
    }
}
