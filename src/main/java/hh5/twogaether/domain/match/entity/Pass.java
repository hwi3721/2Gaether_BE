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
public class Pass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long opponentId;
    private Long dogId;

    private boolean isLoved;

    public Pass(Long opponentId, Long dogId, boolean isLoved) {
        this.opponentId = opponentId;
        this.dogId = dogId;
        this.isLoved = isLoved;
    }
}
