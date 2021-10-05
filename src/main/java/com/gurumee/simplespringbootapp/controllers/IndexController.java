package com.gurumee.simplespringbootapp.controllers;

import com.gurumee.simplespringbootapp.apis.services.GenerateRandomNumberService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class IndexController {
    private final GenerateRandomNumberService generateRandomNumberService;

    public IndexController(GenerateRandomNumberService generateRandomNumberService) {
        this.generateRandomNumberService = generateRandomNumberService;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        int randomNumber = generateRandomNumberService.generate();
        int rest = randomNumber % 4;
        log.debug("rest: " + rest);

        switch (rest) {
            case 0:
                return ResponseEntity.status(HttpStatus.OK).body("ok - 2xx");
            case 1:
                return ResponseEntity.status(HttpStatus.FOUND).body("redirect - 3xx");
            case 2:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error - 4xx");
            case 3:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error - 5xx");
            default:
                throw new RuntimeException("logic error");
        }
    }
}
