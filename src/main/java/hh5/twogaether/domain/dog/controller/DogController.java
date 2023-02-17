package hh5.twogaether.domain.dog.controller;

import hh5.twogaether.domain.dog.dto.DogSignupRequestDto;
import hh5.twogaether.domain.dog.entity.Dog;
import hh5.twogaether.domain.dog.service.DogService;
import hh5.twogaether.domain.image.service.ImageService;
import hh5.twogaether.domain.users.dto.ResponseMessageDto;
import hh5.twogaether.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs") //공용으로 쓰는 주소
public class DogController {
    private final DogService dogService;
    private final ImageService imageService;

    @PostMapping
    private ResponseEntity<ResponseMessageDto> saveDogInfo(@ModelAttribute DogSignupRequestDto dogRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Dog savedDog = dogService.createDog(dogRequestDto, userDetails.getUser());
        List<String> imgUrls = imageService.upload(dogRequestDto.getImages(),savedDog);
        log.info("imgUrls = " + imgUrls);
        return new ResponseEntity<>(new ResponseMessageDto(CREATED.value(), "강아지 정보 저장 완료"), CREATED);
    }

    @PatchMapping
    private ResponseEntity<ResponseMessageDto> patchDogInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody DogSignupRequestDto dogSignupRequestDto){
        dogService.patchMyDog(userDetails.getUser().getId(), dogSignupRequestDto);
        return new ResponseEntity<>(new ResponseMessageDto(OK.value(), "강아지 정보 수정 완료"), OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseMessageDto> deleteDogInfo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        dogService.deleteMyDog(id,userDetails.getUser());

        return new ResponseEntity<>(new ResponseMessageDto(OK.value(), "강아지 정보 삭제 완료"), OK);
    }
}
