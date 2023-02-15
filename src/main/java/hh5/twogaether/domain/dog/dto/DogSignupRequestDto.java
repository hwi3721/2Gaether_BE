package hh5.twogaether.domain.dog.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class DogSignupRequestDto {

    private String dogName;
    private String dogSex;
    private String dogDetails;
    private List<MultipartFile> images;

    public DogSignupRequestDto(String dogName, String dogSex, String dogDetails, List<MultipartFile> images) {
        this.dogName = dogName;
        this.dogSex = dogSex;
        this.dogDetails = dogDetails;
        this.images = images;
    }
}
