package hh5.twogaether.domain.loves;

import hh5.twogaether.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long> {
    Love findByLovedIdAndLovingId(Long lovedId, Long lovingId);
}