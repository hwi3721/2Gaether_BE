package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomListResponseDto {
    private String roomId;
    private String nickname;
    private String message;
    private String dogImageUrl;

}