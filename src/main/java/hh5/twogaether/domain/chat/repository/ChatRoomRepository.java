package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    ChatRoom findByUserId1AndUserId2(Long userId1, Long userId2);
    List<ChatRoom> findByUserId1(Long UserId1);
    List<ChatRoom> findByUserId2(Long UserId2);
}
