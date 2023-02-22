package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static hh5.twogaether.exception.message.ExceptionMessage.NOT_EXISTED_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private Map<Long, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    //UserId를 대조해보고 서로 좋아요가 됐는지 확인하고, 맞을 경우 방 생성하는 로직을 .create()위에 만들고 {}로 묶어주면 될듯
    public ChatRoom createChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetailsImpl) {
        User user = userRepository.findByUsername(createRequestDto.getUserName()).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        ChatRoom chatRoom = ChatRoom.create();

        if(chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(),user))

        chatRoomMap.put(chatRoom.getChatRoomId(), chatRoom);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }


    //밑의 두 로직 하나로 합칠 것 -> 유저의 id와 대조해보고 보이게 할 것이므로 리스트는 하나만 있으면 된다.
    public List findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(Long id) {
        return chatRoomMap.get(id);
    }

}