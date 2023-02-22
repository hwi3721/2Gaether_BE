package hh5.twogaether.domain.dog.repository;

import hh5.twogaether.domain.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    @Query("select d from Dog d where d.isDelete = false")
    Dog findByOrderById(Long id);

    List<Dog> findByOrderById();
    List<Dog> findByCreatedBy(Long createdBy);
}
