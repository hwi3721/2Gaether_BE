package hh5.twogaether.domain.dog.controller;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.service.DogService;
import hh5.twogaether.domain.image.service.ImageService;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs") //공용으로 쓰는 주소
public class DogController {
    private final DogService dogService;
    private final ImageService imageService;

    @PostMapping
    private ResponseEntity saveDogInfo(@ModelAttribute DogSignupRequestDto dogRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Dog savedDog = dogService.createDog(dogRequestDto, userDetails.getUser());
        List<String> imgUrls = imageService.upload(dogRequestDto.getImages(),savedDog);
        log.info("imgUrls = " + imgUrls);
        return new ResponseEntity(202,HttpStatus.ACCEPTED);
    }

    @PatchMapping
    private ResponseEntity patchDogInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody DogSignupRequestDto dogSignupRequestDto){
        dogService.patchMyDog(userDetails.getUser().getId(), dogSignupRequestDto);
        return new ResponseEntity(202,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteDogInfo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        dogService.deleteMyDog(id,userDetails.getUser());

        return new ResponseEntity(202, HttpStatus.ACCEPTED);
    }
}
