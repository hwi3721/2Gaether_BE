package hh5.twogaether.domain.image.entity;

import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

//    private boolean isRepresent = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    public Image(String imgUrl, Dog dog) {
        this.imgUrl = imgUrl;
        this.dog = dog;
    }
}