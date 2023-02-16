package hh5.twogaether.domain.image.dto;

import hh5.twogaether.domain.image.entity.Image;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageResponseDto {

    private Long id;

    private Long createdBy;

    private String imageUrls;

    private LocalDateTime createdAt;

    public ImageResponseDto(Image image) {
        this.id = image.getId();
        this.createdBy = image.getCreatedBy();
        this.createdAt = image.getCreatedAt();
        this.imageUrls = image.getImgUrl();

    }
}