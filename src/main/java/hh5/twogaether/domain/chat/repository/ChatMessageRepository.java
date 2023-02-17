package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.dto.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
