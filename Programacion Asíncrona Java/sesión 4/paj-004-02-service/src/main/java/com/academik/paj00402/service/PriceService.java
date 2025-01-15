package com.academik.paj00402.service;

import com.academik.paj00402.dto.PriceDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RequestScoped
public class PriceService {

    @Inject
    private SleepService sleepService;

    public PriceDTO findBy(Long productId) {

        //Emulando búsqueda a BD
        this.sleepService.sleep(randomNumber -> System.out.println("Consultando:" + productId + ": " + randomNumber));

        var random = new Random();

        var price = BigDecimal.valueOf(random.nextDouble() * 100)
                .setScale(2, RoundingMode.HALF_DOWN);


        //Emulando excepción
        if (random.nextBoolean()) {
            throw new RuntimeException("Error emulado para productId: " + productId);
        }
        //-----

        return PriceDTO.builder()
                .productId(productId)
                .price(price)
                .build();

    }
}
