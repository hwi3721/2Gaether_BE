package hh5.twogaether.domain.dog.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.entity.QDog;
import hh5.twogaether.domain.match.dto.MatchDto;
import hh5.twogaether.domain.match.entity.Match;
import hh5.twogaether.domain.match.entity.QPass;
import hh5.twogaether.domain.users.entity.QUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static hh5.twogaether.domain.dog.entity.QDog.dog;
import static hh5.twogaether.domain.match.entity.QPass.pass;
import static hh5.twogaether.domain.users.entity.QUser.user;

@Slf4j
@Repository
public class DogQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public DogQueryRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    public List<MatchDto> findAllNotDeletedAndNotPassedDog(Long id) {
        List<MatchDto> fetch = queryFactory
                .select(Projections.constructor(MatchDto.class,
                        dog.id,
                        dog.createdBy,
                        dog.user.latitude,
                        dog.user.longitude)
                )
                .from(dog)
                .leftJoin(pass)
                .on(pass.createdBy.eq(id).and(dog.id.eq(pass.dogId)))
                .where(
                        dog.isDelete.eq(false),
                        dog.createdBy.ne(id),
                        pass.dogId.isNull()
                )
                .fetch();
        for (MatchDto dto : fetch) {
            log.info("dto = {}",dto);

        }
        log.info("fetch size = {}", fetch.size());

        return fetch;
    }
}
