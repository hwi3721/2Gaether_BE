package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.loves.repository.LoveRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static hh5.twogaether.exception.message.ExceptionMessage.ALREADY_EXISTED_ROOM;
import static hh5.twogaether.exception.message.ExceptionMessage.NOT_EXISTED_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final LoveRepository loveRepository;
    private final UserRepository userRepository;
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    //UserId를 대조해보고 서로 좋아요가 됐는지 확인하고, 맞을 경우 방 생성하는 로직을 .create()위에 만들고 {}로 묶어주면 될듯
    public ChatRoom createChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findById(createRequestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        //상대도 좋아요를 받았을 경우 같은 채팅방이 두 개 만들어지는 경우 제한 거는 로직(40 ~ 49)

//        Optional<User> userInfo1 = userRepository.findById(user.getId()); //상대 아이디
//        Optional<User> userInfo2 = userRepository.findById(userDetails.getUser().getId()); //내 아이디
//
        ChatRoom createdChatRoom = new ChatRoom(createRequestDto, userDetails);

        //쿼리문 써서 쿼리 한 번 좋지 않겠냐 조언 -> 기본 기능 구현후 찾아보기
        ChatRoom chatRoomInfo1 = chatRoomRepository.findByUserId1AndUserId2(user.getId(),userDetails.getUser().getId());
        ChatRoom chatRoomInfo2 = chatRoomRepository.findByUserId1AndUserId2(userDetails.getUser().getId(),user.getId());

        if (chatRoomInfo1==null && chatRoomInfo2==null) {
            chatRoomRepository.save(createdChatRoom);
        } else {    //if(chatRoom != null ||chatRoom2 != null) -> 인텔리j님이 그냥 else 쓰라고 해서 고침
            throw new IllegalArgumentException(ALREADY_EXISTED_ROOM.getDescription());
        }

      return createdChatRoom;
    }


    //밑의 두 로직 하나로 합칠 것 -> 유저의 id와 대조해보고 보이게 할 것이므로 리스트는 하나만 있으면 된다.
    public List<ChatRoom> findAllRoom(UserDetailsImpl userDetailsImpl) {
        List<ChatRoom> myChatRoom1 = chatRoomRepository.findByUserId1(userDetailsImpl.getUser().getId());
        List<ChatRoom> myChatRoom2 = chatRoomRepository.findByUserId2(userDetailsImpl.getUser().getId());
        if(myChatRoom1==null){
            throw new IllegalArgumentException(NOT_EXISTED_ID.getDescription());
        }

//        List<ChatRoom> chatRooms = new ArrayList<>();
        // 채팅방 생성순서 최근 순으로 반환
//        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(myChatRoom1);
        return myChatRoom1;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

//    public void deleteChatRoom(String id) {
//        chatRoomMap.remove(id);
//        chatRoomRepository.deleteById(id);
//    }

}