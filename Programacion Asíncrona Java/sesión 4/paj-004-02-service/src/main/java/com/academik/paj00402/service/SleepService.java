package com.academik.paj00402.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;
import java.util.function.Consumer;

@ApplicationScoped
public class SleepService {

    private final Random random;

    public SleepService() {
        this.random = new Random();
    }

    public void sleep(Consumer<Integer> randomNumberConsumer) {
        try {
            int min = 2;
            int max = 5;
            int randomNumber = (random.nextInt(max - min + 1) + min) * 1000;

            randomNumberConsumer.accept(randomNumber);

            Thread.sleep(randomNumber);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
