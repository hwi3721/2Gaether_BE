package hh5.twogaether.domain.loves.repository;

import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoveRepository extends JpaRepository<Love, Long> {

    @Query("select l from Love l where l.matchCode = 0 and l.createdBy =:createdBy")
    List<Love> findAllNotAcceptedByCreatedBy(@Param("createdBy") Long createdBy);

    @Query("select l from Love l where l.matchCode = 0 and l.opponent = :opponent")
    List<Love> findAllNotAcceptedByOpponentId(@Param("opponent") User opponentId);

    @Query("select l from Love l where l.me = :me and l.opponent =:opponent")
    Love findByMeAndOpponentId(@Param("me") User opponentId, @Param("opponent") User createdBy);
}