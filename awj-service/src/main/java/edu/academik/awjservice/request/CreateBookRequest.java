package edu.academik.awjservice.request;

import edu.academik.awjservice.domain.Library;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBookRequest {

    @NotNull(message = "Título es requerido.")
    @JsonbProperty("title")
    private String title;

    @NotNull(message = "ISBN es requerido.")
    @JsonbProperty("isbn")
    private String isbn;

    @NotNull(message = "ID de Libería es requerido.")
    @JsonbProperty("libraryId")
    private Long libraryId;

    // JsonIgnore
    @JsonbTransient
    private Library library;
}
