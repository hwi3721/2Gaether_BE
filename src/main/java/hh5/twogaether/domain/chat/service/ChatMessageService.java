package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import hh5.twogaether.domain.users.service.UserService;
import hh5.twogaether.security.jwt.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public void createMessage(ChatMessage message) {

//        String userName = userService.getUserNameByUserId(userId);

//        if(message.getSender().equals())
            if (ChatMessage.MessageType.ENTER.equals(message.getType()))
                message.setMessage(message.getSender() + "님이 입장하셨습니다.");
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            chatMessageRepository.save(message);

        }

    }