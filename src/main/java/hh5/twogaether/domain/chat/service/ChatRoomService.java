package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatMsg;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMsgRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMsgRepository chatMsgRepository;
    private Map<String, ChatRoom> chatRoomMap;
    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;
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

//    public void inviteUser(ChatMessage message){
//        ChatMsg msg = chatMsgRepository.fi
//        if (ChatMessage.MessageType.CHAT.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//        chatMsgRepository.save(message);
//    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom() {
        ChatRoom chatRoom = ChatRoom.create();
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    @Transactional
    public void createChatMsg(ChatMessage message) {
//        Optional<User> memberSender = userRepository.findByNickname(sender);
//        Optional<ChatRoom> chatRoom = chatRoomRepository.findByRoomId(roomId);
//
//        ChatMsg chatMsg = new ChatMsg(chatRoom.get(), chatText, memberSender.get());
        String msg = message.getMessage();
        if(msg !=null){
            ChatMsg chatMsg = new ChatMsg();
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        chatMsgRepository.save(chatMsg);
        }

    }
}