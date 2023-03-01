package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import hh5.twogaether.domain.users.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void createMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        chatMessageRepository.save(message);
    }

}