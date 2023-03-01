package hh5.twogaether.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {
    private String roomId;
    private String userEmail;
    private String message; // 메시지
}

