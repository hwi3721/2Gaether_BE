package hh5.twogaether.domain.match.controller;

import hh5.twogaether.domain.match.dto.MatchResponseDto;
import hh5.twogaether.domain.match.service.MatchService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;

    @GetMapping("")
    public List<MatchResponseDto> showMatchs(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<MatchResponseDto> match = matchService.showmatchs(userDetails.getUser().getId());
        return match;
    }
}
