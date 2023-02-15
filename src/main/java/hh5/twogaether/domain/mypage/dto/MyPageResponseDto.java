package hh5.twogaether.domain.mypage.dto;

import hh5.twogaether.domain.dog.dto.DogResponseDto;
import hh5.twogaether.domain.dog.entity.Dog;
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
    private String password;
    private UserRoleEnum role;
    private Double latitude;
    private Double longitude;
//    private List<Dog> myDogs = new ArrayList<>();  // entity를 반환하려면 즉시로딩을 해야하는데 그러면 서버 성능에 안좋음 DogResponseDto로 받아와야함.
    private List<DogResponseDto> myDogs = new ArrayList<>();
    public MyPageResponseDto(User user) {
//        this.email = user.getUsername();
        this.username = user.getNickname();
//        this.password = user.getPassword();
        this.role = user.getRole();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
//        List<DogResponseDto> realMyDogs = new ArrayList<>();
        this.myDogs = user.getDogs().stream()
                .map(dog -> new DogResponseDto(dog))
                .collect(Collectors.toList());


//            if (!dog.isDelete()) {
//                this.myDogs.add(new DogResponseDto(dog));
//                log.info("myDogs = " + myDogs.size());
//            }
//        }
    }
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