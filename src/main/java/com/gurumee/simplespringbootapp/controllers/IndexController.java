package com.gurumee.simplespringbootapp.controllers;

import com.gurumee.simplespringbootapp.apis.services.GenerateRandomNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class IndexController {
    private final GenerateRandomNumberService generateRandomNumberService;

    public IndexController(GenerateRandomNumberService generateRandomNumberService) {
        this.generateRandomNumberService = generateRandomNumberService;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        int randomNumber = generateRandomNumberService.generate();

        switch (randomNumber % 4) {
            case 0:
                return ResponseEntity.status(HttpStatus.OK).body("ok - 2xx");
            case 1:
                return ResponseEntity.status(HttpStatus.FOUND).body("redirect - 3xx");
            case 2:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error - 4xx");
            case 3:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error - 5xx");
        }

        throw new RuntimeException("logic error");
    }
}
