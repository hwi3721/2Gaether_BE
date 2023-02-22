package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

@Getter
public class LoveReceivedDto {

    private Long id;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveReceivedDto(Love love, Dog dog) {
        this.id = love.getCreatedBy();
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.imageUrl = dog.getDogImages().get(0).getImgUrl();
    }
}
