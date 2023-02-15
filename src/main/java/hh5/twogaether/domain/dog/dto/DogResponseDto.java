package hh5.twogaether.domain.dog.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DogResponseDto {
    private String dogName;
    private String dogSex;
    private String dogDetails;

    public DogResponseDto(Dog dog) {
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.dogDetails = dog.getDogDetails();
    }
}
