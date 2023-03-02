package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomRealResponseDto {
    private String roomId;
    private Long messageId;
    private String userEmail;
    private String userNickname;
    private String message;

    public ChatRoomRealResponseDto(ChatMessage chatMessage) {
        this.roomId = chatMessage.getRoomId();
        this.messageId = chatMessage.getId();
        this.userEmail = chatMessage.getSender().getUsername();
        this.userNickname = chatMessage.getSender().getNickname();
        this.message = chatMessage.getMessage();
    }
}

