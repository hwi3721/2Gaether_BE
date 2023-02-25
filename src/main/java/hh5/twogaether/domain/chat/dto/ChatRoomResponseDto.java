package hh5.twogaether.domain.chat.dto;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.users.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChatRoomResponseDto {
    private String roomId;
    private String nickname;
//    private String message;


    public ChatRoomResponseDto(ChatRoom chatRoom, String nickname){//, String message) {
        this.roomId = chatRoom.getRoomId();
        this.nickname = nickname;
        }
    }