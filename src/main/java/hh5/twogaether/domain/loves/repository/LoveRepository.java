package hh5.twogaether.domain.loves.repository;

import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoveRepository extends JpaRepository<Love, Long> {

    @Query("select l from Love l where l.matchCode = 0 and l.createdBy =:createdBy")
    List<Love> findAllNotAcceptedByCreatedBy(@Param("createdBy") Long createdBy);

    @Query("select l from Love l where l.matchCode = 0 and l.opponentId = :opponentId")
    List<Love> findAllNotAcceptedByOpponentId(@Param("opponentId") Long opponentId);

    @Query("select l from Love l where l.opponentId = :opponentId and l.createdBy =:createdBy")
    Love findByOpponentIdAndCreatedBy(@Param("opponentId") Long opponentId, @Param("createdBy") Long createdBy);
}