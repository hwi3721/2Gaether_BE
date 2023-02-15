package hh5.twogaether.domain.gmail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailService {
    String sendSimpleMessage(String to, String username)throws Exception;
    String emailLinkCheck(String emailLink, String userEmail);
}
