package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoomId(String roomId);
    List<ChatMessage> findAllByRoomIdOrderById(String roomId);
    Optional<ChatMessage> findTopByRoomIdOrderByIdDesc(String roomId);
//    ChatMessage findTopByChatRoomOrderByCreatedAtDesc(String message);
//    ChatMessage findByRoomId(String roomId);
}
