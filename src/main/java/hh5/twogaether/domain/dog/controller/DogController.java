package hh5.twogaether.domain.dog.controller;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.service.DogService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs") //공용으로 쓰는 주소
public class DogController {
    private final DogService dogService;

    @PostMapping
    private ResponseEntity saveDogInfo(@RequestBody DogSignupRequestDto dogRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        dogService.createDog(dogRequestDto,userDetails.getUser());
        return new ResponseEntity(202,HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    private ResponseEntity patchDogInfo(@PathVariable long id,@RequestBody DogSignupRequestDto dogSignupRequestDto,Dog dog){
        dogService.patchMyDog(id,dogSignupRequestDto,dog);
        return new ResponseEntity(202,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteDogInfo(@PathVariable long id){
        dogService.deleteMyDog(id);
        return new ResponseEntity(202, HttpStatus.ACCEPTED);
    }
}
