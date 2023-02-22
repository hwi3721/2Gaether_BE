package hh5.twogaether.domain.loves;

import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long> {
    Love findByLovedIdAndLovingId(Long lovedId, Long lovingId);

//    @Query("select l from love l where l.matchCode = 1")
//    User findById1andId2(Long id1, Long id2);

}