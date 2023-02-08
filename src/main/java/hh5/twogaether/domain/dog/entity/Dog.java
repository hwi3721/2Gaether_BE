package hh5.twogaether.domain.dog.entity;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Dog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private Long id;

    @Column(nullable = false)
    private String dogName;

    @Column(nullable = false)
    private String dogSex;

    @Column(nullable = false)
    private String dogDetails;

    private boolean isDelete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Dog(DogSignupRequestDto dogSignupRequestDto) {
        this.dogName = dogSignupRequestDto.getDogName();
        this.dogSex = dogSignupRequestDto.getDogSex();
        this.dogDetails = dogSignupRequestDto.getDogDetails();
        this.isDelete = dogSignupRequestDto.getIsDelete();
    }

    public void Dog(DogSignupRequestDto dogSignupRequestDto) {
        this.dogName = dogSignupRequestDto.getDogName();
        this.dogSex = dogSignupRequestDto.getDogSex();
        this.dogDetails = dogSignupRequestDto.getDogDetails();
        this.isDelete = dogSignupRequestDto.getIsDelete();
    }

}

