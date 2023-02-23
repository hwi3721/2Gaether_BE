package hh5.twogaether.domain.match.repository;

import hh5.twogaether.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m where m.isPassed = false and m.createdBy =:createdBy")
    List<Match> findAllNotPassedByCreatedBy(@Param("createdBy") Long createdBy);

    @Query("select m from Match m where m.dogId = :dogId and m.createdBy =:createdBy")
    Match findByDogIdAndCreatedBy(@Param("dogId") Long dogId, @Param("createdBy") Long createdBy);

    List<Match> findByCreatedBy(Long createdBy);

    @Modifying(clearAutomatically = true)
    @Query("delete from Match m where m.createdBy = :createdBy")
    void deleteAllByCreatedBy(@Param("createdBy") Long createdBy);
}
