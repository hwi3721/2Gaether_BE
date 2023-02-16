package hh5.twogaether.domain.chat.repository;

import hh5.twogaether.domain.chat.entity.ChatRoom;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

}
