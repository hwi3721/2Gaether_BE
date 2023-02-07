package hh5.twogaether.domain.match.service;

import hh5.twogaether.domain.match.dto.MatchResponseDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {
    private final UserRepository userRepository;

    public List<MatchResponseDto> showmatchs(Long id) {
        List<User> users = userRepository.findAll();
        User me = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아무튼 안됨")
        );
        List<MatchResponseDto> matchs = new ArrayList<>();
        for (User user : users) {
            if (!user.isDelete()) {
                if (5 >= roundDistance(calculateDistance(me.getLatitude(), me.getLongitude(), user.getLatitude(),user.getLongitude()))) {
                    MatchResponseDto matchResponseDto = new MatchResponseDto(user);
                    matchs.add(matchResponseDto);
                    shuffle(matchs);
                }
            }

        }
        return matchs;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //Kilometers
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
