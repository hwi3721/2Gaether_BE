package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class LoveSentDto {

    private Long userId;

    private Long dogId;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveSentDto(Love love) {
        this.userId = love.getOpponent().getId();
        this.dogId = love.getOpponent().getDogs().get(0).getId();
        this.dogName = love.getOpponent().getDogs().stream()
                .filter(dog -> dog.isDelete() == false)
                .collect(Collectors.toList()).get(0).getDogName();
        this.dogSex = love.getOpponent().getDogs().get(0).getDogSex();
        this.imageUrl = love.getOpponent().getDogs().get(0).getDogImages().get(0).getImgUrl();
    }
}
