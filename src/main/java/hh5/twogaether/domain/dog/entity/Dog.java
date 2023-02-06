package hh5.twogaether.domain.dog.entity;

import hh5.twogaether.domain.dog.dto.DogRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@RequiredArgsConstructor
public class Dog /*extends BaseEntity*/{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogId;
    private String dogName;
    private String dogSex;
    private String dogDetails;
    private boolean isDelete = false;
    public Dog(DogRequestDto dogRequestDto) {
        this.dogName = dogRequestDto.getDogName();

    }
}
