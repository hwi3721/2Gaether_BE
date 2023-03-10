package hh5.twogaether.domain.loves.service;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.loves.dto.LoveReceivedDto;
import hh5.twogaether.domain.loves.dto.LoveSentDto;
import hh5.twogaether.domain.loves.entity.Love;
import hh5.twogaether.domain.loves.repository.LoveRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
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
    private final UserRepository userRepository;

    //좋아요, 수락 공통 로직
    @Transactional
    public Love loveUser(Long dogId, User me) {
        Dog foundDog = dogRepository.findById(dogId).orElseThrow(
                ()-> new IllegalArgumentException("그런 개는 없습니다.")
        );
        User opponent = userRepository.findNotDeletedUserById(foundDog.getCreatedBy());
        Love sendCase = loveRepository.findByMeAndOpponentId(me, opponent);
        Love acceptCase = loveRepository.findByMeAndOpponentId(opponent, me);
        if (sendCase == null && acceptCase == null && me != opponent) {
            sendCase = loveRepository.save(new Love(me, opponent));
        }
        if (acceptCase != null && !acceptCase.getCreatedBy().equals(me.getId())) {
            acceptCase.accept();
            return acceptCase;
        }
        return sendCase;
    }

    // 거절
    @Transactional
    public void rejectLove(Long dogId, User me) {
        Dog foundDog = dogRepository.findById(dogId).orElseThrow(
                ()-> new IllegalArgumentException("그런 개는 없습니다.")
        );
        Long opponentId = foundDog.getCreatedBy();
        User opponent = userRepository.findNotDeletedUserById(opponentId);
        Love foundLove = loveRepository.findByMeAndOpponentId(opponent, me);
        if (foundLove.getMatchCode() != 1) {
            foundLove.reject();
        }
    }

    //보낸 좋아요 조회
    public List<LoveSentDto> getSentLove(Long id) {
        List<LoveSentDto> sent = loveRepository.findAllNotAcceptedByCreatedBy(id).stream()
                .map(love -> new LoveSentDto(love))
                .collect(Collectors.toList());
        return sent;
    }

    //받은 좋아요 조회
    public List<LoveReceivedDto> getReceivedLove(User me) {
        List<LoveReceivedDto> received = loveRepository.findAllNotAcceptedByOpponentId(me).stream()
                .map(love -> new LoveReceivedDto(love))
                .collect(Collectors.toList());
        return received;
    }
}
