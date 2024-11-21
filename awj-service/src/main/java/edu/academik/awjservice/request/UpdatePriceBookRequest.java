package edu.academik.awjservice.request;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePriceBookRequest {

    @JsonbProperty("bookId")
    private Long bookId;

    @JsonbProperty("priceCalculatorTypeId")
    private Integer priceCalculatorTypeId;
}
