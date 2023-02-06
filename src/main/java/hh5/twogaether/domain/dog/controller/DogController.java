package hh5.twogaether.domain.dog.controller;

import hh5.twogaether.domain.dog.dto.DogRequestDto;
import hh5.twogaether.domain.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dogs")
public class DogController {
    private final DogService dogService;

//    @PostMapping()
//    private ResponseEntity<?> saveDogInformation{
//        return dogService.createDog();
//    }
}
