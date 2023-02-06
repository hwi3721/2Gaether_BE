package hh5.twogaether.domain.dog.service;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.repository.DogRepository;
import hh5.twogaether.domain.dog.entity.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;

    public String createDog(DogSignupRequestDto dogSignupRequestDto){
        Dog dog = dogRepository.findByDogId(dogSignupRequestDto.getDogName());
         dogRepository.save(dog);
        return "";
    }

}