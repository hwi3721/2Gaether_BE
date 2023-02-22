package hh5.twogaether.domain.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.mypage.dto.MyPageRequestDto;
import hh5.twogaether.domain.users.dto.SignUpRequestDto;
import hh5.twogaether.util.TimeStamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(nullable = false)
    private String username;    // 이게 email이 들어오는 필드
    private String password;

    private Double latitude;   //  위도
    private Double longitude;  //  경도
    private String detailAddress;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int emailCheck;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Dog> dogs = new ArrayList<>();

    private boolean isDelete = false;

    private int range = 20;

    public User(String nickname, String email, String provider) {
        this.nickname = nickname;
        this.username = email;
        this.password = provider;
    }

    public User(SignUpRequestDto signupRequestDto) {
        this.nickname = signupRequestDto.getUsername();
        this.username = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.role = signupRequestDto.getUserRole();
    }

    public void updateUserEmailCheck() {
        this.emailCheck = 1;
    }

    public void patchUser(MyPageRequestDto myPageRequestDto) {
        this.nickname = (myPageRequestDto.getUsername() == null) ? this.getNickname() : myPageRequestDto.getUsername();
        this.latitude = (myPageRequestDto.getLatitude() == null) ? this.getLatitude() : myPageRequestDto.getLatitude();
        this.longitude = (myPageRequestDto.getLongitude() == null) ? this.getLongitude() : myPageRequestDto.getLongitude();
        this.detailAddress = (myPageRequestDto.getAddress() == null) ? this.getDetailAddress() : myPageRequestDto.getAddress();
        this.range = (myPageRequestDto.getRange() == 0) ? this.getRange() : myPageRequestDto.getRange();
    }

    public void deleteUser() {
        this.isDelete = true;
    }
}
