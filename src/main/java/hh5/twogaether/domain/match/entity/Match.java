package hh5.twogaether.domain.match.entity;

import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long opponentId;

    private int distance;

    private boolean isPassed = false;

    public Match(Long opponent, int distance) {
        this.opponentId = opponent;
        this.distance = distance;
    }

    public void passUser() {
        this.isPassed = true;
    }
}
