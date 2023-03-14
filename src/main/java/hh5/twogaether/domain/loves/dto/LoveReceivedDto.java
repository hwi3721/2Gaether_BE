package hh5.twogaether.domain.loves.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.loves.entity.Love;
import lombok.Getter;

@Getter
public class LoveReceivedDto {

    private Long userId;

    private Long dogId;

    private String dogName;

    private String dogSex;

    private String imageUrl;

    public LoveReceivedDto(Love love) {
        this.userId = love.getCreatedBy();
        this.dogId = love.getMe().getDogs().get(0).getId();
        this.dogName = love.getMe().getDogs().get(0).getDogName();
        this.dogSex = love.getMe().getDogs().get(0).getDogSex();
        this.imageUrl = love.getMe().getDogs().get(0).getDogImages().isEmpty()
                ? "https://midcon.s3.ap-northeast-2.amazonaws.com/be7648fa-5b71-47c4-8b9f-a1defc6a28cc.jpg"
                : love.getMe().getDogs().get(0).getDogImages().get(0).getImgUrl();


        //        this.imageUrl = love.getMe().getDogs().get(0).getDogImages().get(0).getImgUrl();
    }
}
