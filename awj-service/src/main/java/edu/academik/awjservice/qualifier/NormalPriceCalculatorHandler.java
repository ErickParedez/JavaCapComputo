package edu.academik.awjservice.qualifier;

import java.math.BigDecimal;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;

@ApplicationScoped
@PriceCalculator(PriceCalculatorType.NORMAL)
@Log
public class NormalPriceCalculatorHandler implements PriceCalculatorHandler {

    @Override
    public BigDecimal getPrice() {
        log.info("Calculando 100  ...");
        return BigDecimal.valueOf(100);
    }

}
