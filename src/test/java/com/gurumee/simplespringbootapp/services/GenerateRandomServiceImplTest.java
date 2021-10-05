package com.gurumee.simplespringbootapp.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateRandomServiceImplTest {
    private final GenerateRandomServiceImpl generateRandomService = new GenerateRandomServiceImpl();

    @Test
    @DisplayName("random 숫자 생성, 0-100 까지 발생하는가?")
    public void test(){
        for (int i=0; i<Integer.MAX_VALUE; i++) {
            int num = generateRandomService.generate();
            assertTrue(0 <= num && num <= 100);
        }
    }
}