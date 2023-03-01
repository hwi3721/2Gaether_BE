package hh5.twogaether.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomInformDto {
    private String roomId;
    private String nickname;
    private String opponentNickname;
}
