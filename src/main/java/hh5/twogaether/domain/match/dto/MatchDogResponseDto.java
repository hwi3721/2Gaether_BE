package hh5.twogaether.domain.match.dto;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.image.dto.ImageResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MatchDogResponseDto {
    private Long dogId;
    private String dogName;
    private String dogSex;
    private String dogDetails;
    private Long createdBy;
    private int distance;
    private List<ImageResponseDto> images = new ArrayList<>();


    public MatchDogResponseDto(Dog dog, int distance) {
        this.dogId = dog.getId();
        this.dogName = dog.getDogName();
        this.dogSex = dog.getDogSex();
        this.dogDetails = dog.getDogDetails();
        this.createdBy = dog.getCreatedBy();
        this.distance = distance;
        this.images = dog.getDogImages().stream()
                .map(image -> new ImageResponseDto(image))
                .collect(Collectors.toList());
    }
}