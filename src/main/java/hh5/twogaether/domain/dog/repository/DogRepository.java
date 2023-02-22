package hh5.twogaether.domain.dog.repository;

import hh5.twogaether.domain.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByOrderById();
    List<Dog> findByCreatedBy(Long createdBy);
}
