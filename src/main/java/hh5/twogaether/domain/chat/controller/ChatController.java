package hh5.twogaether.domain.chat.controller;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    @SendTo("/topic/public")
    public void message(@Payload ChatMessage message) {
        chatMessageService.saveMessage(message);
    }

}