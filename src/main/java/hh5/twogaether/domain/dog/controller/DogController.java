package hh5.twogaether.domain.dog.controller;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs") //공용으로 쓰는 주소
public class DogController {
    private final DogService dogService;

    @PostMapping
    private void saveDogInformation(@RequestBody DogSignupRequestDto dogRequestDto){
        dogService.createDog(dogRequestDto);
    }

    @GetMapping
    private List<Dog> showDogInformation(){
        return dogService.showDogs();
    }

    @PatchMapping("/{id}")
    private void patchDogInformation(@PathVariable long id,@RequestBody DogSignupRequestDto dogSignupRequestDto,Dog dog){
        dogService.patchMyDog(id,dogSignupRequestDto,dog);
    }
}
