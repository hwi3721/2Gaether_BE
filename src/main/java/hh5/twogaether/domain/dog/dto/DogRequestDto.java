package hh5.twogaether.domain.dog.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.List;

@Getter
public class DogRequestDto {
    private String dogName;
    private String dogSex;
    private List<MultipartFile> dogImageUrls;
    private String dogDetail;
}
