package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatMsg;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMsgRepository extends JpaRepository<ChatMsg,Long> {
    List<ChatMsg> findAllByChatRoom(ChatRoom chatRoom);
}
