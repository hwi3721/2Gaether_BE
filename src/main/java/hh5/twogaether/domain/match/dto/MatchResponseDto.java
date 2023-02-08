package hh5.twogaether.domain.match.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class MatchResponseDto {
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;
    private List<Dog> myDogs = new ArrayList<>();

    public MatchResponseDto(User user) {
//        this.email = user.getUsername();
        this.username = user.getNickname();
//        this.password = user.getPassword();
//        this.role = user.getRole();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
        this.myDogs = user.getDogs();
    }
}