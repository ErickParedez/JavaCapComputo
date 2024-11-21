package edu.academik.awjservice.qualifier;

import java.math.BigDecimal;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;

@ApplicationScoped
@PriceCalculator(PriceCalculatorType.EXTENDED)
@Log
public class ExtendedPriceCalculatorHandler implements PriceCalculatorHandler {

    @Override
    public BigDecimal getPrice() {
        log.info("Calculando 247  ...");
        return BigDecimal.valueOf(247);
    }

}
