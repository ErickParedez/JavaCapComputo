package edu.academik.awjservice.qualifier;

import lombok.Getter;

@Getter
public enum PriceCalculatorType {

    NORMAL(1), EXTENDED(2);

    private final int id;

    private PriceCalculatorType(int id) {
        this.id = id;
    }

}
