package edu.academik.awjservice.websocket.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {
    private String username;

    private String message;
}
