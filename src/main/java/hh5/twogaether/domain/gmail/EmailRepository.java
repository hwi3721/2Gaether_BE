package hh5.twogaether.domain.gmail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailValidation, Long> {
    Optional<EmailValidation> findByLink(String link);
}
