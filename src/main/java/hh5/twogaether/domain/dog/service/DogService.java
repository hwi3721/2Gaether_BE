package hh5.twogaether.domain.dog.service;

import hh5.twogaether.domain.dog.dto.DogImageRequestDto;
import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.dog.entity.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;

    // 회원 가입시 입력해야하는 강아지 정보
    public void createDog(DogSignupRequestDto dogSignupRequestDto){
        Dog dog = new Dog(dogSignupRequestDto);
        dogRepository.save(dog);
    }

    // 강아지 사진 보여줌 -> 산책 친구 매칭에서 사용될 것
    public List<Dog> showDogs() {
        return dogRepository.findByOrderByDogId();
    }

    //
    @Transactional
    public void patchMyDog(long id, DogSignupRequestDto dogSignupRequestDto, Dog dog) {
        dog = dogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("강아지의 아이디가 존재하지 않습니다.")
        );

        dog.Dog(dogSignupRequestDto);
    }

    }