package hh5.twogaether.domain.users.entity;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.dto.UserInfoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)  // email 중복 안됨
    private String email;
    @Column(nullable = false, unique = true)  // username 겹쳐도 되므로
    private String username;
    private String password;
//    private String stringAddress;   //위도경도좌표 -> 한글 주소 변환이 어려울 시 사용
    private Double latitude;   //  위도
    private Double longitude;  //  경도
    private List<Dog> dogs = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserInfoRequestDto userInfoRequestDto) {
        this.email = userInfoRequestDto.getEmail();
        this.username = userInfoRequestDto.getUsername();
        this.password = userInfoRequestDto.getPassword();
        this.role = userInfoRequestDto.getRole();
        this.latitude = userInfoRequestDto.getLatitude();
        this.longitude = userInfoRequestDto.getLongitude();
    }
}
