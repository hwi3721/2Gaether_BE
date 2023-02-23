package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.security.UserDetailsImpl;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String roomId;

    private Long userId1;
    private Long userId2;

    public ChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails) {
        this.roomId = UUID.randomUUID().toString();
        this.userId1 = createRequestDto.getUserId();
        this.userId2 = userDetails.getUser().getId();
    }

//    public static ChatRoom create() {
//        ChatRoom chatRoom = new ChatRoom();
//        return chatRoom;
//    }

//
//    public void invited(User user1, User user2) {
//        this.user1 = user1;
//        this.user2 = user2;
//    }

}