package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.dto.ChatRoomResponseDto;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static hh5.twogaether.exception.message.ExceptionMessage.ALREADY_EXISTED_ROOM;
import static hh5.twogaether.exception.message.ExceptionMessage.NOT_EXISTED_ID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    //UserId를 대조해보고 서로 좋아요가 됐는지 확인하고, 맞을 경우 방 생성하는 로직을 .create()위에 만들고 {}로 묶어주면 될듯
    public void createChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails) {
        Long userId1 = createRequestDto.getUserId();
        Long userId2 = userDetails.getUser().getId();

        User user = userRepository.findById(userId1).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        String nickname1 = user.getNickname();
        String nickname2 = userDetails.getUser().getNickname();

        //이거 엔터티에서 다시 설정해주고 리스트쪽에서 불러와야할듯
//        String nickname = userId1.equals(userId2)
//                ? userDetails.getUser().getNickname() : user.getNickname();

        ChatRoom createdChatRoom = new ChatRoom(createRequestDto, userDetails, nickname1,nickname2);
        ChatRoom chatRoomInfo = chatRoomRepository.findByUserId1AndUserId2(user.getId(),userId2);

        //채팅방이 두 개 만들어지는 경우 제한 거는 조건문 + 같은 유저 Id로는 채팅방 안 만들어지게 구현
        if (chatRoomInfo==null && userId1!=userId2) {
            chatRoomRepository.save(createdChatRoom);
        } else {    //if(chatRoom != null ||chatRoom2 != null) -> 인텔리j님이 그냥 else 쓰라고 해서 고침
            throw new IllegalArgumentException(ALREADY_EXISTED_ROOM.getDescription());
        }

    }

    //밑의 두 로직 하나로 합칠 것 -> 유저의 id와 대조해보고 보이게 할 것이므로 리스트는 하나만 있으면 된다.
    public List<ChatRoomResponseDto> findAllRoom(UserDetailsImpl userDetails) {

        Long useId = userDetails.getUser().getId();
        List<ChatRoom> myChatRoom = chatRoomRepository.findByUserId1OrUserId2(useId, useId);

        List<ChatRoomResponseDto> chatRooms = new ArrayList<>();
        for (ChatRoom chatRoom : myChatRoom) {
            ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto();
            chatRoomResponseDto.setRoomId(chatRoom.getRoomId());
            Long otherUserId = chatRoom.getUserId1().equals(useId) ? chatRoom.getUserId2() : chatRoom.getUserId1();
            User otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
            chatRoomResponseDto.setNickname(otherUser.getNickname());

            chatRooms.add(chatRoomResponseDto);
        }
        return chatRooms;
    }

//    public ChatRoom findRoomById(String roomId, UserDetailsImpl userDetails) {
//        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
//        Long useId = userDetails.getUser().getId();
//        List<ChatRoom> myChatRoom = chatRoomRepository.findByUserId1OrUserId2(useId, useId);
//
//        List<ChatRoomResponseDto> chatRooms = new ArrayList<>();
//        for (ChatRoom chatRoom : myChatRoom) {
//            ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto();
//            chatRoomResponseDto.setRoomId(chatRoom.getRoomId());
//            Long otherUserId = chatRoom.getUserId1().equals(useId) ? chatRoom.getUserId2() : chatRoom.getUserId1();
//            User otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
//            chatRoomResponseDto.setNickname(otherUser.getNickname());
//
//            chatRooms.add(chatRoomResponseDto);
//        }
//        return chatRooms;
//    }


//        return chatRoomMap.get(id);
//    }

//    public void deleteChatRoom(String id) {
//        chatRoomMap.remove(id);
//        chatRoomRepository.deleteById(id);
//    }

}