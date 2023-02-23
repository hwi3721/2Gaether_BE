package hh5.twogaether.domain.image.dto;

import hh5.twogaether.domain.image.entity.Image;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageResponseDto {

    private Long id;

    private String imageUrl;

    public ImageResponseDto(Image image) {
        this.id = image.getId();
        this.imageUrl = image.getImgUrl();

    }
}