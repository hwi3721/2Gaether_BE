package hh5.twogaether.domain.chat.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChatRoomResponseDto {
    private String nickname;
    private String message;
    private LocalDate sentTime;

}
