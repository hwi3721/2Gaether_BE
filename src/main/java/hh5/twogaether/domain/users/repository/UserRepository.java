package hh5.twogaether.domain.users.repository;

import hh5.twogaether.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.isDelete = false ")
    List<User> findAllNotDeletedUser();
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String username);
    Optional<User> findById(Long id);

    List<User> findAll();

//    User findById1andId2(Long id1, Long id2);
}
