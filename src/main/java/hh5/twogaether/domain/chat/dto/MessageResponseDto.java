package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String userNickname;
    private String message;

    public MessageResponseDto(ChatRoom chatRoom, ChatMessage chatMessage) {
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.userNickname = chatMessage.getSender().getNickname();
        this.message = chatMessage.getMessage();
    }
}
