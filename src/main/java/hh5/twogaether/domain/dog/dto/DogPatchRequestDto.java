package hh5.twogaether.domain.dog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DogPatchRequestDto {
    private String dogName;
    private String dogSex;
    private String dogDetails;

    public DogPatchRequestDto(String dogName, String dogSex, String dogDetails) {
        this.dogName = dogName;
        this.dogSex = dogSex;
        this.dogDetails = dogDetails;
    }
}
