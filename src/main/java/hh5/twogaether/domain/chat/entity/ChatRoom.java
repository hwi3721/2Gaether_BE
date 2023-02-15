package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.domain.users.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String roomId;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        return chatRoom;
    }

}