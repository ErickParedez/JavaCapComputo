package com.academik.paj00402clientint.resources.dto;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
public class PriceDTO {

    @JsonbProperty("productoId")
    private Long productId;

    @JsonbProperty("precio")
    private BigDecimal price;
}
