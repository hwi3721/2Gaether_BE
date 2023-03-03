package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.*;
import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static hh5.twogaether.exception.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;


    public void createChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails) {
        Long userId1 = createRequestDto.getUserId();
        Long userId2 = userDetails.getUser().getId();

        User user = userRepository.findById(userId1).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        String nickname1 = user.getNickname();
        String nickname2 = userDetails.getUser().getNickname();

        ChatRoom createdChatRoom = new ChatRoom(createRequestDto, userDetails, nickname1,nickname2);
        ChatRoom chatRoomInfo = chatRoomRepository.findByUserId1AndUserId2(user.getId(),userId2);

        if (chatRoomInfo==null && !userId1.equals(userId2)) {
            chatRoomRepository.save(createdChatRoom);
        } else {
            throw new IllegalArgumentException(ALREADY_EXISTED_ROOM.getDescription());
        }

    }

    public List<ChatRoomListResponseDto> findAllRoom(UserDetailsImpl userDetails) {

        Long myId = userDetails.getUser().getId();
        List<ChatRoom> myChatRoom = chatRoomRepository.findByUserId1OrUserId2(myId, myId);

        List<ChatRoomListResponseDto> chatRooms = new ArrayList<>();
        for (ChatRoom chatRoom : myChatRoom) {
            ChatRoomListResponseDto chatRoomListResponseDto = new ChatRoomListResponseDto();
            chatRoomListResponseDto.setRoomId(chatRoom.getRoomId());
            Long otherUserId = chatRoom.getUserId1().equals(myId) ? chatRoom.getUserId2() : chatRoom.getUserId1();
            User otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
            chatRoomListResponseDto.setNickname(otherUser.getNickname());
//            ChatMessage lastMessage = chatMessageRepository.findTopByChatRoomOrderByCreatedAtDesc(chatRoom.getLastMessage());
//            if (lastMessage != null) {
//                chatRoomListResponseDto.setMessage(lastMessage.getMessage());
//            }

            chatRooms.add(chatRoomListResponseDto);
        }
        return chatRooms;
    }

    public InformAndMessageListDto getRoomById(UserDetailsImpl userDetails, String roomId) {
        Long myId = userDetails.getUser().getId();

        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatRoomInformDto roomInformDto = new ChatRoomInformDto();
        roomInformDto.setRoomId(chatRoom.getRoomId());
        Long myUserId = chatRoom.getUserId1().equals(myId) ? chatRoom.getUserId1() : chatRoom.getUserId2();
        User myInform = userRepository.findById(myUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
        roomInformDto.setNickname(myInform.getNickname());
        roomInformDto.setEmail(myInform.getUsername());
        Long otherUserId = chatRoom.getUserId1().equals(myId) ? chatRoom.getUserId2() : chatRoom.getUserId1();
        User otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
        roomInformDto.setOpponentNickname(otherUser.getNickname());

        //log.info(roomId);
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByRoomId(roomId);

        List<MessageResponseDto> messagesResponseDtos = new ArrayList<>();

        for (ChatMessage chatMessage : chatMessages) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(chatMessage); // + Sender
            messagesResponseDtos.add(messageResponseDto);
        }

        return new InformAndMessageListDto(roomInformDto,messagesResponseDtos);
    }

//    public void deleteChatRoom(String id) {
//        chatRoomMap.remove(id);
//        chatRoomRepository.deleteById(id);
//    }

}
