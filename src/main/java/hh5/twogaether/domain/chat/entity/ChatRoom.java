package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.domain.chat.dto.ChatRoomCreateRequestDto;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.security.UserDetailsImpl;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom extends BaseEntity{

    @Id
    @Column(nullable = false,unique = true)
    private String roomId;
    private Long userId1;  //수락당한 사람 아이디
    private Long userId2;  //만든 사람 아이디
    private String nickname1;
    private String nickname2;
    private String lastMessage = "";
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dog_id")
//    private Dog dog;


    public ChatRoom(ChatRoomCreateRequestDto createRequestDto, UserDetailsImpl userDetails, String nickname1, String nickname2){//, Dog dog) {
        this.roomId = UUID.randomUUID().toString();
        this.userId1 = createRequestDto.getUserId();
        this.userId2 = userDetails.getUser().getId();
        this.nickname1 = nickname1;
        this.nickname2 = nickname2;
//        this.dog = dog;
    }

    // 좋아요 or 수락 시 채팅방 생성자
    public ChatRoom(Long userId1, Long userId2, String nickname1, String nickname2) {
        this.roomId = UUID.randomUUID().toString();
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.nickname1 = nickname1;
        this.nickname2 = nickname2;
    }

    public void ChatRoomLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}