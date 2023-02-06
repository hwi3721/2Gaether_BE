package hh5.twogaether.domain.users.entity;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.dto.UserInfoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String nickname;    // username이 들어오는 필드

    //username을 spring에서 사용중이라 중복 허용 시 spring과 충돌
    //(front)username -> (back)nickname
    //(front)email -> (back)username@Column(nullable = false, unique = true)                                            //(front)email -> (back)username
    private String username;    // 이게 email이 들어오는 필드
    private String password;
//    private String stringAddress;   //위도경도좌표 -> 한글 주소 변환이 어려울 시 사용
    private Double latitude;   //  위도
    private Double longitude;  //  경도

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dog> dogs = new ArrayList<>();
    private boolean isDelete = false;
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserInfoRequestDto userInfoRequestDto) {
        this.nickname = userInfoRequestDto.getUsername();
        this.username = userInfoRequestDto.getEmail();
        this.password = userInfoRequestDto.getPassword();
        this.role = userInfoRequestDto.getRole();
        this.latitude = userInfoRequestDto.getLatitude();
        this.longitude = userInfoRequestDto.getLongitude();
    }

    public void UserPatch(UserInfoRequestDto userInfoRequestDto) {
        this.nickname = (userInfoRequestDto.getUsername() == null) ? this.getNickname() : userInfoRequestDto.getUsername();
        this.username = (userInfoRequestDto.getEmail() == null) ? this.getUsername() : userInfoRequestDto.getEmail();
        this.latitude = (userInfoRequestDto.getLatitude() == null) ? this.getLatitude() : userInfoRequestDto.getLatitude();
        this.longitude = (userInfoRequestDto.getLongitude() == null) ? this.getLongitude() : userInfoRequestDto.getLongitude();
    }
    public void UserDelete() {
        this.isDelete = true;
    }
}
