package hh5.twogaether.domain.loves.dto;

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
                .filter(dog -> !dog.isDelete())
                .collect(Collectors.toList()).get(0).getDogName();
        this.dogSex = love.getOpponent().getDogs().get(0).getDogSex();
        this.imageUrl = love.getOpponent().getDogs().get(0).getDogImages().isEmpty()
                ? "https://midcon.s3.ap-northeast-2.amazonaws.com/be7648fa-5b71-47c4-8b9f-a1defc6a28cc.jpg"
                : love.getOpponent().getDogs().get(0).getDogImages().get(0).getImgUrl();
    }
}
