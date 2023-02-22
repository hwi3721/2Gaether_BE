package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.domain.chat.dto.ChatRoomDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    //    @Column(nullable = false,unique = true)
//    private String roomId;
//    private Long invitedFirst;
//    private Long invitedSecond;
    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2;

    public static ChatRoom create() {
        ChatRoom chatRoom = new ChatRoom();
        return chatRoom;
    }

    public void Invited(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

}