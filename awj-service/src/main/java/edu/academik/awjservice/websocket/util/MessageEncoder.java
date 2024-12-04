package edu.academik.awjservice.websocket.util;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class MessageEncoder implements Encoder.Text<ChatMessage> {
    private Jsonb jsonb;

    @Override
    public void init(EndpointConfig config) {
        jsonb = JsonbBuilder.create();
    }

    @Override
    public String encode(ChatMessage message) throws EncodeException {
        return jsonb.toJson(message);
    }

    @Override
    public void destroy() {
        try {
            jsonb.close();
        } catch (Exception ignored) {

        }
    }
}
