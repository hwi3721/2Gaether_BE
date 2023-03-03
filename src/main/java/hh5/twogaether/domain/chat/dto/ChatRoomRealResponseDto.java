package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.security.UserDetailsImpl;
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

    public ChatRoomRealResponseDto(ChatMessage chatMessage) {// , UserDetailsImpl userDetails
        this.roomId = chatMessage.getRoomId();
        this.messageId = chatMessage.getId();
        this.userEmail =
//                userDetails.getUsername();
                chatMessage.getSender().getUsername();
        this.userNickname =
//                userDetails.getUser().getNickname();
                chatMessage.getSender().getNickname();
        this.message = chatMessage.getMessage();
    }
}