package hh5.twogaether.domain.dog.entity;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Dog /*extends BaseEntity*/{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogId;

    @Column(nullable = false)
    private String dogName;

    @Column(nullable = false)
    private String dogSex;

    @Column(nullable = false)
    private String dogDetails;

    @Column(nullable = false)
    private boolean isDelete = false;
    public Dog(DogSignupRequestDto dogSignupRequestDto) {
        this.dogName = dogSignupRequestDto.getDogName();

    }
}

