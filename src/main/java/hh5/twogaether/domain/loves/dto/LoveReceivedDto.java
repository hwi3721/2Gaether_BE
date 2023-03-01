package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

@Getter
public class LoveReceivedDto {

    private Long userId;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveReceivedDto(Love love) {
        this.userId = love.getCreatedBy();
        this.dogName = love.getMe().getDogs().get(0).getDogName();
        this.dogSex = love.getMe().getDogs().get(0).getDogSex();
        this.imageUrl = love.getMe().getDogs().get(0).getDogImages().get(0).getImgUrl();
    }
}
