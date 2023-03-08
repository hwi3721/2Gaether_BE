package hh5.twogaether.domain.chat.controller;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.dto.ChatRoomListResponseDto;
import hh5.twogaether.domain.chat.dto.InformAndMessageListDto;
import hh5.twogaether.domain.chat.service.ChatRoomService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    // 채팅방 생성  -> 일부러 주석처리해둠 필요없으면 삭제
//    @PostMapping("/rooms")
//    public void createRoom(@RequestBody ChatRoomCreateRequestDto createRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        chatRoomService.createChatRoom(createRequest, userDetails);
//    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoomListResponseDto> listRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.findAllRoom(userDetails);
    }

    //톡방
    @GetMapping("/rooms/{roomId}")
    @ResponseBody
    public InformAndMessageListDto lookMessage(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("roomId") String roomId) {
        return chatRoomService.getRoomById(userDetails, roomId);
    }
}
