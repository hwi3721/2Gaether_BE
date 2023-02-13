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
    @ManyToOne
    @JoinColumn(name = "loving_user_id")
    private User lovingUser;
    @ManyToOne
    @JoinColumn(name = "loved_user_id")
    private User lovedUser;

    public Love(User lovedUser, User lovingUser) {
        this.lovedUser = lovedUser;
        this.lovingUser = lovingUser;
    }
}
