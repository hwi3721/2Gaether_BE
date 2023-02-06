package hh5.twogaether.domain.dog.repository;

import hh5.twogaether.domain.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByDogId(String dogName);
}
