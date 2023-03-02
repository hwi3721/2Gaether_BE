package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.ChatMessageDto;
import hh5.twogaether.domain.chat.dto.ChatRoomRealResponseDto;
import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void createMessage(ChatMessageDto chatMessageDto) {
        Optional<User> user = userRepository.findByUsername(chatMessageDto.getUserEmail());
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(chatMessageDto.getRoomId());

        ChatMessage chatMessage = new ChatMessage(user.get(), chatMessageDto.getMessage(), chatRoom.getRoomId());
        chatMessageRepository.save(chatMessage);

        ChatRoomRealResponseDto chatRoomRealResponseDto = new ChatRoomRealResponseDto(chatMessage);

        chatRoom.ChatRoomLastMessage(chatMessageDto.getMessage());
        chatRoomRepository.save(chatRoom);
        messagingTemplate.convertAndSend("/sub/chat/rooms/" + chatMessageDto.getRoomId(), chatRoomRealResponseDto);
    }

}
