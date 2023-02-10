package hh5.twogaether.domain.gmail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GmailService {

    String sendSimpleMessage(GmailRequestDto to)throws Exception;
}
