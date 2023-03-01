package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import hh5.twogaether.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoomId(String roomId);
//    ChatMessage findTopByChatRoomOrderByCreatedAtDesc(String message);
}
