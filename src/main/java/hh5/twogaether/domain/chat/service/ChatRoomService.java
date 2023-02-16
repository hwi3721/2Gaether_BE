package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private Map<String, ChatRoom> chatRoomMap;
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(){//(UserDetailsImpl userDetailsImpl) {

//        Optional<User> user = userRepository.findByUsername(userDetailsImpl.getUsername());
//
//        if (user.isEmpty()) {
//            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
//        }
//
//        if (chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(), user.get()).isPresent()
//                || chatRoomRepository.findByUser1AndUser2(user.get(), userDetailsImpl.getUser()).isPresent())
//        {
//            Optional<ChatRoom> chatRoom1 = chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(), user.get());
//            Optional<ChatRoom> chatRoom2 = chatRoomRepository.findByUser1AndUser2(user.get(), userDetailsImpl.getUser());
//        }

        ChatRoom chatRoom = ChatRoom.create();
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        log.info("됐나?" );
        chatRoomRepository.save(chatRoom);
        System.out.println("챗룸 서비스" + chatRoom);
        return chatRoom;
    }

}