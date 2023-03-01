package hh5.twogaether.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InformAndMessageListDto {
    private ChatRoomInformDto informDto;
    private List<MessageResponseDto> chats;

    public InformAndMessageListDto(ChatRoomInformDto informDto, List<MessageResponseDto> MessagesResponseDto) {
        this.informDto = informDto;
        this.chats = MessagesResponseDto;
    }

}