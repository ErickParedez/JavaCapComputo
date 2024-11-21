package edu.academik.awjservice.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookUpdated {

    Long bookId;
}
