package edu.academik.awjservice.dto;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlElement;
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

    @XmlElement(name = "bookIdWS")
    public Long getBookIdWS() {
        return this.bookId;
    }
}
