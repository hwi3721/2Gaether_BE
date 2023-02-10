package hh5.twogaether.domain.users.entity;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.mypage.dto.MyPageRequestDto;
import hh5.twogaether.domain.users.dto.SignUpRequestDto;
import hh5.twogaether.util.TimeStamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

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
    private String detailAddress;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dog> dogs = new ArrayList<>();
    private boolean isDelete = false;
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String nickname, String email, String providerId) {
        this.nickname = nickname;
        this.username = email;
        this.password = providerId;
    }

    public User(SignUpRequestDto signupRequestDto) {
        this.nickname = signupRequestDto.getUsername();
        this.username = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.role = signupRequestDto.getUserRole();
    }

    public void patchUser(MyPageRequestDto myPageRequestDto) {

        this.nickname = (myPageRequestDto.getUsername() == null) ? this.getNickname() : myPageRequestDto.getUsername();
        this.username = (myPageRequestDto.getEmail() == null) ? this.getUsername() : myPageRequestDto.getEmail();
        this.latitude = (myPageRequestDto.getLatitude() == null) ? this.getLatitude() : myPageRequestDto.getLatitude();
        this.longitude = (myPageRequestDto.getLongitude() == null) ? this.getLongitude() : myPageRequestDto.getLongitude();
        this.detailAddress = (myPageRequestDto.getDetailAddress() == null) ? this.getDetailAddress() : myPageRequestDto.getDetailAddress();
    }

    public void deleteUser() {
        this.isDelete = true;
    }

}
