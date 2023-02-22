package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

@Getter
public class LoveSentDto {

    private Long id;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveSentDto(Love love, Dog dog) {
        this.id = love.getOpponentId();
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.imageUrl = dog.getDogImages().get(0).getImgUrl();
    }
}
