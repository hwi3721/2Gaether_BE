package hh5.twogaether.domain.loves.entity;

import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Love extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long opponentId;

    //0 = 한 명만 좋아요, 1 = 둘 다 좋아요, 2 = 거절 / 차단
    private int matchCode = 0;

    public Love(Long opponentId) {
        this.opponentId = opponentId;
    }

    public void accept() {
        this.matchCode = 1;
    }
    public void reject() {
        this.matchCode = 2;
    }
}
