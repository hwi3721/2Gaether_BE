package hh5.twogaether.domain.match.dto;

import hh5.twogaether.domain.dog.dto.DogResponseDto;
import hh5.twogaether.domain.users.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MatchResponseDto {
    private String email;
    private String username;
    private int distance;
    private List<DogResponseDto> myDogs = new ArrayList<>();

    public MatchResponseDto(User user, int distance) {
        this.email = user.getUsername();
        this.username = user.getNickname();
        this.distance = distance;
        this.myDogs = user.getDogs().stream()
                .map(dog -> new DogResponseDto(dog))
                .collect(Collectors.toList());
    }
}