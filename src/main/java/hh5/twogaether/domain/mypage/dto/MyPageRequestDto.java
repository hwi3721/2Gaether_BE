package hh5.twogaether.domain.mypage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageRequestDto {

    private String username;
    private String password;
    private String newPassword;
    private Double latitude;
    private Double longitude;
    private String address;
    private int range;

    public MyPageRequestDto(MyPageRequestDto myPageRequestDto, String encryptPassword) {
        this.username = myPageRequestDto.getUsername();
        this.password = encryptPassword;
        this.newPassword = myPageRequestDto.getNewPassword();
        this.latitude = myPageRequestDto.getLatitude();
        this.longitude = myPageRequestDto.getLongitude();
        this.address = myPageRequestDto.getAddress();
        this.range = myPageRequestDto.getRange();
    }
}
