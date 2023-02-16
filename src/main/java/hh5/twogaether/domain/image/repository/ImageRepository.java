package hh5.twogaether.domain.image.repository;

import hh5.twogaether.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
