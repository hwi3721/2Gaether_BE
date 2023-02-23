package hh5.twogaether.domain.match.entity;

import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Matches")
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dogId;

    private Long opponentId;

    private int distance;

    private boolean isPassed = false;

    public Match(Long dogId, Long opponent, int distance) {
        this.dogId = dogId;
        this.opponentId = opponent;
        this.distance = distance;
    }

    public void passUser() {
        this.isPassed = true;
    }
}
