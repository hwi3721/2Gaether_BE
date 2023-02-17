package hh5.twogaether.domain.chat.controller;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService messageService;

    @MessageMapping("/chat/message")
    @SendTo("/topic/public")
    public void message(ChatMessage message) {
        messageService.createMessage(message);
    }
}