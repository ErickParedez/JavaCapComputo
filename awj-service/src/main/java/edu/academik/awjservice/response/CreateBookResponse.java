package edu.academik.awjservice.response;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBookResponse {
    
    @JsonbProperty("bookId")
    Long bookId;
}
