package hh5.twogaether.domain.dog.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import lombok.Getter;

@Getter
public class DogMyPageDto {
    private Long dogId;
    private String dogName;
    private String dogSex;
    private String dogDetails;
    private String profileImages;

    public DogMyPageDto(Dog dog) {
        this.dogId = dog.getId();
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.dogDetails = dog.getDogDetails();
        this.profileImages = dog.getDogImages().get(0).getImgUrl();
    }
}
