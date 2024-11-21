package edu.academik.awjservice.request;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBookRequest {

    @JsonbProperty("title")
    private String title;

    @JsonbProperty("isbn")
    private String isbn;

    @JsonbProperty("libraryId")
    private Long libraryId;
}
