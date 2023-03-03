package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.security.UserDetailsImpl;
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
//                chatMessage.getSender().getNickname();
        this.message = chatMessage.getMessage();
    }
}
