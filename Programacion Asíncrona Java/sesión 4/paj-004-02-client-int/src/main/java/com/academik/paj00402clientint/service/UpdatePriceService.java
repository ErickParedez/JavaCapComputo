package com.academik.paj00402clientint.service;

import com.academik.paj00402clientint.resources.dto.PriceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;

import java.util.logging.Level;

@ApplicationScoped
@Log
public class UpdatePriceService {

    public void updatePrice(PriceDTO priceDTO) {

        log.log(Level.INFO, "Actualizando precio: {0}.", priceDTO);
    }

    public void updatePriceToZero(Long productId, String message) {
        log.log(Level.WARNING, "Actualizando precio: {0} con mensaje: {1}.", new Object[]{productId, message});
    }
}
