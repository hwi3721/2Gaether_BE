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
public class ChatMessage {//extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;
//    private String sender;
    private String message;

    public ChatMessage(String roomId, User sender, String message) {// String sender
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

}
