package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String roomId;
//    @ManyToOne
//    private User user1;
//    @ManyToOne
//    private User user2;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        return chatRoom;
    }

//    public void stockUserId(ChatRoomUserRequestDto userRequestDto) {
//        this.user1 = (userRequestDto.getChatUserId()==null)? this.user1: userRequestDto.getChatUserId();
//        this.user2 = (userRequestDto.getChatUserId()==null)? this.user2: userRequestDto.getChatUserId();
//    }

}