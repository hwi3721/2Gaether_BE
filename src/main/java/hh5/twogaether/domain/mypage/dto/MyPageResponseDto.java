package hh5.twogaether.domain.mypage.dto;

import hh5.twogaether.domain.dog.dto.DogMyPageDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class MyPageResponseDto {
    private String email;
    private String username;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;
    private List<DogMyPageDto> myDogs = new ArrayList<>();
    public MyPageResponseDto(User user) {
        this.email = user.getUsername();
        this.username = user.getNickname();
        this.role = user.getRole();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
        this.myDogs = user.getDogs().stream()
                .filter(dog -> !dog.isDelete())
                .map(dog -> new DogMyPageDto(dog))
                .collect(Collectors.toList());

    }
}