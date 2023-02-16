package hh5.twogaether.domain.dog.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.image.dto.ImageResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class DogResponseDto {
    private String dogName;
    private String dogSex;
    private String dogDetails;
    private List<ImageResponseDto> dogImages;


    public DogResponseDto(Dog dog) {
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.dogDetails = dog.getDogDetails();
        this.dogImages = dog.getDogImages().stream()
                .map(image -> new ImageResponseDto(image))
                .collect(Collectors.toList());
    }
}
