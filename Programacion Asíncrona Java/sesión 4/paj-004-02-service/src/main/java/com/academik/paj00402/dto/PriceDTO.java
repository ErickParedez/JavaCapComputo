package com.academik.paj00402.dto;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PriceDTO {

    @JsonbProperty("productoId")
    Long productId;

    @JsonbProperty("precio")
    BigDecimal price;

}
