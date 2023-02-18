package hh5.twogaether.domain.loves;

import hh5.twogaether.domain.users.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "love")
@Getter
@NoArgsConstructor
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long lovingId;
    private Long lovedId;
    private Integer matchCode = 0;  //  0 = 한 명만 좋아요, 1 = 둘 다 좋아요, 2 = 차단/dislike

    public Love(Long lovedId, Long lovingId) {
        this.lovedId = lovedId;
        this.lovingId = lovingId;
    }
    public Love(Long lovedId, Long lovingId, Integer c) {
        this.lovedId = lovedId;
        this.lovingId = lovingId;
        this.matchCode = c;
    }
}
