package edu.academik.awjservice.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDTO {

    Long bookId;

    String title;

    String isbn;

    String libraryName;

    BigDecimal price;

}
