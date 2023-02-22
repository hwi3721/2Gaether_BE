package hh5.twogaether.domain.loves.service;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.loves.dto.LoveReceivedDto;
import hh5.twogaether.domain.loves.dto.LoveSentDto;
import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.loves.repository.LoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoveService {

    private final LoveRepository loveRepository;
    private final DogRepository dogRepository;

    @Transactional
    public void loveUser(Long opponentId, Long myId) {
        Love acceptCase = loveRepository.findByOpponentIdAndCreatedBy(myId, opponentId);
        Love sendCase = loveRepository.findByOpponentIdAndCreatedBy(opponentId, myId);
        if (sendCase == null && acceptCase == null) {
            loveRepository.save(new Love(opponentId));
        }
        if (acceptCase != null) {
            acceptCase.accept();
        }
    }

    @Transactional
    public void rejectLove(Long opponentId, Long myId) {
        Love foundLove = loveRepository.findByOpponentIdAndCreatedBy(myId, opponentId);
        foundLove.reject();
    }

    //보낸 좋아요 조회
    public List<LoveSentDto> getSentLove(Long id) {
        Dog foundDog = dogRepository.findByCreatedBy(id).get(0);
        List<LoveSentDto> loving = loveRepository.findAllNotAcceptedByCreatedBy(id).stream()
                .map(love -> new LoveSentDto(love, foundDog))
                .collect(Collectors.toList());
        return loving;
    }

    //받은 좋아요 조회
    public List<LoveReceivedDto> getReceivedLove(Long id) {
        Dog foundDog = dogRepository.findByCreatedBy(id).get(0);
        List<LoveReceivedDto> loved = loveRepository.findAllNotAcceptedByOpponentId(id).stream()
                .map(love -> new LoveReceivedDto(love, foundDog))
                .collect(Collectors.toList());
        return loved;
    }


}
