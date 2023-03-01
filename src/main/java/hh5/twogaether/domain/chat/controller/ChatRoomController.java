package hh5.twogaether.domain.chat.controller;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.dto.ChatRoomResponseDto;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.service.ChatRoomService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    // 채팅방 생성
    @PostMapping("/rooms")
    public void createRoom(@RequestBody ChatRoomCreateRequestDto createRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        chatRoomService.createChatRoom(createRequest, userDetails);
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoomResponseDto> listRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatRoomService.findAllRoom(userDetails);
    }

//    @GetMapping("/rooms/{roomId}")
//    public ChatRoom getChatRooms(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable String roomId) {
//        return chatRoomService.findRoomById(userDetails,roomId);
//    }

}

//    @GetMapping("/rooms/{roomId}")
//    @ResponseBody
//    public ChatRoom roomInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return chatRoomService.findRoomById(userDetails);
//    }

////    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }

