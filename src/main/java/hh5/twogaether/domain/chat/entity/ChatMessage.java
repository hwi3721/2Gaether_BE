package hh5.twogaether.domain.chat.entity;

import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hh5.twogaether.domain.users.entity.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom roomId;
    //    private String sender;
    @ManyToOne
    private User sender;
    private String message;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//    @ManyToOne
//    private User user;
//    @ManyToOne
//    private User user2;
}
