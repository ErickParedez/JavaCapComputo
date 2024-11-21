package edu.academik.awjservice.qualifier;

import jakarta.enterprise.util.AnnotationLiteral;

public class PriceCalculatorTypeAnnotationLiteral extends AnnotationLiteral<PriceCalculator>
        implements PriceCalculator {

    private final PriceCalculatorType priceCalculatorType;

    public PriceCalculatorTypeAnnotationLiteral(PriceCalculatorType priceCalculatorType) {
        this.priceCalculatorType = priceCalculatorType;
    }

    @Override
    public PriceCalculatorType value() {
        return this.priceCalculatorType;
    }

}
