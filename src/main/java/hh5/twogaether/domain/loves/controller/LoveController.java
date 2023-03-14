package hh5.twogaether.domain.loves.controller;

import hh5.twogaether.domain.chat.service.ChatRoomService;
import hh5.twogaether.domain.loves.dto.LoveReceivedDto;
import hh5.twogaether.domain.loves.dto.LoveSentDto;
import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.loves.service.LoveService;
import hh5.twogaether.domain.match.service.MatchService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/loves")
public class LoveController {

    private final LoveService loveService;
    private final ChatRoomService chatRoomService;
    private final MatchService matchService;

    //보낸 좋아요
    @GetMapping("/sent")
    public ResponseEntity showSentLove(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<LoveSentDto> sentLove = loveService.getSentLove(userDetails.getUser().getId());
        return new ResponseEntity<>(sentLove, OK);
    }

    //받은 좋아요
    @GetMapping("/received")
    public ResponseEntity showReceivedLove(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<LoveReceivedDto> receivedLove = loveService.getReceivedLove(userDetails.getUser());
        return new ResponseEntity(receivedLove, OK);
    }

    //수락
    @PostMapping("/accept/{dogId}")
    public ResponseEntity acceptLove(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long dogId) {
        Love love = loveService.loveUser(dogId, userDetails.getUser());
        chatRoomService.createChatRoom(love);
        matchService.loveUser(dogId, userDetails.getUser().getId());
        return new ResponseEntity<>("매칭 완료", CREATED);
    }

    //거절
    @PostMapping("/reject/{dogId}")
    public ResponseEntity rejectLove(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable Long dogId) {
        loveService.rejectLove(dogId, userDetails.getUser());
        return new ResponseEntity<>("거절 완료", CREATED);
    }
}
