package hh5.twogaether.domain.dog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.image.entity.Image;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "dog", orphanRemoval = true)
    private List<Image> dogImages = new ArrayList<>();

    public Dog(DogSignupRequestDto dogSignupRequestDto, User user) {
        this.dogName = dogSignupRequestDto.getDogName();
        this.dogSex = dogSignupRequestDto.getDogSex();
        this.dogDetails = dogSignupRequestDto.getDogDetails();
        this.user = user;
    }

    public void patchDog(DogSignupRequestDto dogSignupRequestDto) {
        this.dogName = (dogSignupRequestDto.getDogName()==null)? this.getDogName() : dogSignupRequestDto.getDogName();
        this.dogSex = (dogSignupRequestDto.getDogSex()==null)? this.getDogSex() : dogSignupRequestDto.getDogSex();
        this.dogDetails = (dogSignupRequestDto.getDogDetails()==null)? this.getDogDetails() : dogSignupRequestDto.getDogDetails();
    }

    public void deleteDog(){
        this.isDelete = true;
    }

}
