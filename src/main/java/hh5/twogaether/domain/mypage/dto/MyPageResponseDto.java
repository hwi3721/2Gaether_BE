package hh5.twogaether.domain.mypage.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.entity.UserRoleEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MyPageResponseDto {
    private String email;
    private String username;
    private String password;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;
    private List<Dog> myDogs = new ArrayList<>();
    public MyPageResponseDto(User user) {
        this.email = user.getUsername();
        this.username = user.getNickname();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
        List<Dog> realMyDogs = new ArrayList<>();
        for (Dog dog : user.getDogs()) {
            if (!dog.isDelete()){
                realMyDogs.add(dog);
            }
        }
        this.myDogs = realMyDogs;
    }

//    public UserInfoResponseDto(UserInfoRequestDto userInfoRequestDto) {
//        this.email = userInfoRequestDto.getUsername();
//        this.username = userInfoRequestDto.getEmail();
//        this.password = userInfoRequestDto.getPassword();
//        this.role = userInfoRequestDto.getRole();
//        this.latitude = userInfoRequestDto.getLatitude();
//        this.longitude = userInfoRequestDto.getLongitude();
//        this.myDogs = userInfoRequestDto.getDogs();
//    }
}
