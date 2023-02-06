package hh5.twogaether.domain.users.entity;

import hh5.twogaether.domain.users.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)  // email 중복 안됨
    private String email;
    @Column(nullable = false, unique = false)  // username 겹쳐도 되므로
    private String username;
    private String password;
//    private String stringAddress;   //위도경도좌표 -> 한글 주소 변환이 어려울 시 사용
    private Double latitude;   //  위도
    private Double longitude;  //  경도
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserRequestDto userRequestDto) {
        this.email = userRequestDto.getEmail();
        this.username = userRequestDto.getUsername();
        this.password = userRequestDto.getPassword();
        this.role = userRequestDto.getRole();
        this.latitude = userRequestDto.getLatitude();
        this.longitude = userRequestDto.getLongitude();
    }
}
