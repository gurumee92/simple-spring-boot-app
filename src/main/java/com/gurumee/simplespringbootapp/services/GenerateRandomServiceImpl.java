package com.gurumee.simplespringbootapp.services;

import com.gurumee.simplespringbootapp.apis.services.GenerateRandomNumberService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GenerateRandomServiceImpl implements GenerateRandomNumberService {

    @Override
    public int generate() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }
}
