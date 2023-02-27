package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

@Getter
public class LoveSentDto {

    private Long userId;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveSentDto(Love love) {
        this.userId = love.getOpponent().getId();
        this.dogName = love.getOpponent().getDogs().get(0).getDogName();
        this.dogSex = love.getOpponent().getDogs().get(0).getDogSex();
        this.imageUrl = love.getOpponent().getDogs().get(0).getDogImages().get(0).getImgUrl();
    }
}
