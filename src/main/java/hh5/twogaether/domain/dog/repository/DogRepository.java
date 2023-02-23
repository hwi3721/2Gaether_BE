package hh5.twogaether.domain.dog.repository;

import hh5.twogaether.domain.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    @Query("select d from Dog d where d.isDelete = false")
    Dog findByOrderById(Long id);

    @Query("select d from Dog d where d.isDelete = false")
    List<Dog> findAllNotDeletedDog();
    
    List<Dog> findByCreatedBy(Long createdBy);
}
