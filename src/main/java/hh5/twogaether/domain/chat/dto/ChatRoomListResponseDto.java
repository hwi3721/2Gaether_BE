package hh5.twogaether.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomListResponseDto {
    private String roomId;
    private String nickname;
    private String message;


//    public ChatRoomResponseDto(ChatRoom chatRoom, String nickname){//, String message) {
//        this.roomId = chatRoom.getRoomId();
//        this.nickname = nickname;
//        }
    }