package hh5.twogaether.domain.chat.service;

import hh5.twogaether.domain.chat.dto.*;
import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.chat.repository.ChatMessageRepository;
import hh5.twogaether.domain.chat.repository.ChatRoomRepository;
import hh5.twogaether.domain.loves.entity.Love;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static hh5.twogaether.exception.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;


    //아마 안쓸듯 주석처리해둠
//    public void createChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails) {
//        Long userId1 = createRequestDto.getUserId();
//        Long userId2 = userDetails.getUser().getId();
//
//        String nickname1 = user.getNickname();
//        String nickname2 = userDetails.getUser().getNickname();
//        ChatRoom createdChatRoom = new ChatRoom(createRequestDto, userDetails, nickname1,nickname2);//,dog.getDogImages().get(0));
//        ChatRoom chatRoomInfo = chatRoomRepository.findByUserId1AndUserId2(user.getId(),userId2);
//        User user = userRepository.findById(userId1).orElseThrow(
//                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
//        );
//        String nickname1 = user.getNickname();
//        String nickname2 = userDetails.getUser().getNickname();
//
//        ChatRoom createdChatRoom = new ChatRoom(createRequestDto, userDetails, nickname1,nickname2);
//        ChatRoom chatRoomInfo = chatRoomRepository.findByUserId1AndUserId2(user.getId(),userId2);
//
//        if (chatRoomInfo==null && !userId1.equals(userId2)) {
//            chatRoomRepository.save(createdChatRoom);
//        } else {
//            throw new IllegalArgumentException(ALREADY_EXISTED_ROOM.getDescription());
//        }
//    }

    //새로운 채팅방 생성 메서드
    @Transactional
    public void createChatRoom(Love love) {
        Long userId1 = love.getMe().getId();
        Long userId2 = love.getOpponent().getId();

        String nickname1 = love.getMe().getNickname();
        String nickname2 = love.getOpponent().getNickname();

        ChatRoom createdChatRoom = new ChatRoom(userId1, userId2, nickname1, nickname2);
        ChatRoom chatRoomInfo = chatRoomRepository.findByUserId1AndUserId2(userId1, userId2);


        if (chatRoomInfo == null && !userId1.equals(userId2)) {
            chatRoomRepository.save(createdChatRoom);
        } else {
            throw new IllegalArgumentException(ALREADY_EXISTED_ROOM.getDescription());
        }
    }

    public List<ChatRoomListResponseDto> findAllRoom(UserDetailsImpl userDetails) {

        Long myId = userDetails.getUser().getId();
        List<ChatRoom> myChatRoom = chatRoomRepository.findByUserId1OrUserId2OrderByModifiedAt(myId, myId);

        List<ChatRoomListResponseDto> chatRooms = new ArrayList<>();
        for (ChatRoom chatRoom : myChatRoom) {
            ChatRoomListResponseDto chatRoomListResponseDto = new ChatRoomListResponseDto();
            chatRoomListResponseDto.setRoomId(chatRoom.getRoomId());
            Long otherUserId = chatRoom.getUserId1().equals(myId) ? chatRoom.getUserId2() : chatRoom.getUserId1();
            User otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
            chatRoomListResponseDto.setNickname(otherUser.getNickname());
            chatRoomListResponseDto.setDogImageUrl(otherUser.getDogs().get(0).getDogImages().get(0).getImgUrl());

//            List<ChatMessage> lastMessage = chatMessageRepository.findAllByRoomId(chatRoom.getRoomId());
//            log.info(lastMessage.get(0).toString());

//            List<ChatMessage> lastMessage = chatMessageRepository.findAllByRoomIdOrderById(chatRoom.getRoomId());
            Optional<ChatMessage> lastMessage = chatMessageRepository.findTopByRoomIdOrderByIdDesc(chatRoom.getRoomId());

            String last = "";
            if (lastMessage.isPresent()) {
                last = lastMessage.get().getMessage();
                log.info(last);
            }

            chatRoomListResponseDto.setMessage(last);
            chatRooms.add(chatRoomListResponseDto);
        }

        Collections.sort(chatRooms, Comparator.comparing((ChatRoomListResponseDto c) -> c.getMessage() == null ? "" : c.getMessage())
                .thenComparing(ChatRoomListResponseDto::getRoomId, Comparator.naturalOrder()));

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
        return new InformAndMessageListDto(roomInformDto, messagesResponseDtos);
    }

    public void deleteChatRoom(UserDetailsImpl userDetails, String id) {
//        chatRoomMap.remove(id);
        Long myId = userDetails.getUser().getId();
        userRepository.findById(myId).orElseThrow(() -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription()));
        chatRoomRepository.deleteByRoomId(id);
    }

}
