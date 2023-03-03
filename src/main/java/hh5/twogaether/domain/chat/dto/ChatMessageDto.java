package hh5.twogaether.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {
    private String roomId;
    private String sender;
    private String message; // 메시지
}

