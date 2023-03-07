package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    @Query("SELECT c FROM ChatRoom c WHERE (c.userId1 = :userId1 AND c.userId2 = :userId2) OR (c.userId1 = :userId2 AND c.userId2 = :userId1)")
    ChatRoom findByUserId1AndUserId2(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
    List<ChatRoom> findByUserId1OrUserId2(Long userId1, Long userId2);
    ChatRoom findByRoomId(String roomId);
    ChatRoom deleteByRoomId(String roomId);
//    List<ChatRoom> findByUserId1(Long UserId1);
//    List<ChatRoom> findByUserId2(Long UserId2);
}
