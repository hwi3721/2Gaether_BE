package hh5.twogaether.domain.dog.service;

import hh5.twogaether.domain.dog.dto.DogResponseDto;
import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.users.entity.User;
import hh5.twogaether.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static hh5.twogaether.exception.message.ExceptionMessage.NOT_EXISTED_ID;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    // 회원 가입시 입력해야하는 강아지 정보
    @Transactional
    public Dog createDog(DogSignupRequestDto dogSignupRequestDto, User user) {
        Dog dog = new Dog(dogSignupRequestDto, user);

        dogRepository.save(dog);
        return dog;
    }

    public DogResponseDto showYourDog(Long id, User user){
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        userRepository.findById(user.getId()).orElseThrow(
                ()-> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );
        if (dog.isDelete()) throw new IllegalArgumentException("삭제된 강아지");
        if(!(user.getId().equals(dog.getCreatedBy()))){
            throw new IllegalArgumentException(NOT_EXISTED_ID.getDescription());
        }
        return new DogResponseDto(dog);
    }

    //강아지 정보 수정
    @Transactional
    public void patchMyDog(Long id, User user, DogSignupRequestDto dogSignupRequestDto) {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        userRepository.findById(user.getId()).orElseThrow(
                ()-> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );
        if (dog.isDelete()) throw new IllegalArgumentException("삭제된 강아지");
        if(user.getId().equals(dog.getCreatedBy())) {
            dog.patchDog(dogSignupRequestDto);
        }
    }

    @Transactional
    public void deleteMyDog(Long id,User user) {

        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );
        userRepository.findById(user.getId()).orElseThrow(
                ()-> new IllegalArgumentException(NOT_EXISTED_ID.getDescription())
        );

        if(user.getId().equals(dog.getCreatedBy())){
            dog.deleteDog();
        }
    }

}