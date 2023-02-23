package hh5.twogaether.domain.match.service;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.match.dto.MatchDogResponseDto;
import hh5.twogaether.domain.match.entity.Match;
import hh5.twogaether.domain.match.repository.MatchRepository;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.shuffle;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public MatchDogResponseDto getMatches(Long id) {
        List<Dog> dogs = dogRepository.findAllNotDeletedDog();
        User me = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        //기존에 저장된 리스트가 있을 시 기존 정보 사용, 없을 시 새 리스트 저장
        if (matchRepository.findByCreatedBy(id).size() == 0) {
            saveMatchedDogs(id, dogs, me);
        }
        //좋아요, 싫어요 하지 않은 목록 불러와서 셔플
        List<Match> matches = getAndShuffleMatches(id);

        //다 넘겨서 남은 매칭상대가 없는 경우 Matches 다 지우고 다시 갱신
        if (matches.size() == 0) {
            matchRepository.deleteAllByCreatedBy(id);
            List<Dog> renewedDogs = dogRepository.findAllNotDeletedDog();
            User renewedMe = userRepository.findById(id).orElseThrow(
                    ()-> new IllegalArgumentException("아무튼 안됨")
            );
            saveMatchedDogs(id, renewedDogs, renewedMe);
            matches = getAndShuffleMatches(id);
        }
        //matches 에서 1개 뽑아서 보여줌
        int distance = matches.get(0).getDistance();
        Long dogId = matches.get(0).getDogId();
        Dog dog = dogRepository.findById(dogId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        return new MatchDogResponseDto(dog, distance);
    }

    //좋아요, 싫어요 하지 않은 목록 불러와서 셔플
    private List<Match> getAndShuffleMatches(Long id) {
        List<Match> matches = matchRepository.findAllNotPassedByCreatedBy(id);
        shuffle(matches);
        log.info("size = {}",matches.size());
        return matches;
    }

    // 좋아요, 싫어요
    @Transactional
    public void passUser(Long dogId, Long myId) {
        Match opponent = matchRepository.findByDogIdAndCreatedBy(dogId, myId);
        opponent.passUser();
    }

    //Match 리스트 저장
    public void saveMatchedDogs(Long id, List<Dog> dogs, User me) {
        for (Dog dog : dogs) {
            double calculatedDistance = calculateDistance(me.getLatitude(), me.getLongitude(), dog.getUser().getLatitude(), dog.getUser().getLongitude());
            int roundDistance = roundDistance(calculatedDistance);
            if ( me.getRanges() >= roundDistance && !dog.getCreatedBy().equals(id) ) {
                matchRepository.save(new Match(dog.getId(),dog.getUser().getId(),roundDistance));
            }
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //Kilometers
        log.info("distance = "+earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)));
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }

    //거리 반올림하기
    private int roundDistance(double distance) {
        if (Math.round(distance) < 1) {
            return 1;
        }
        return (int) Math.round(distance);
    }
}
