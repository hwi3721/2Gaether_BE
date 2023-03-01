package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {

    private String userNickname;
    private String message;

    public MessageResponseDto(ChatMessage chatMessage) {
        this.userNickname = chatMessage.getSender().getNickname();
        this.message = chatMessage.getMessage();
    }
}
