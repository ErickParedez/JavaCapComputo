package edu.academik.awjservice.websocket.util;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class MessageDecoder implements Decoder.Text<ChatMessage> {
    private Jsonb jsonb;

    @Override
    public void init(EndpointConfig config) {
        jsonb = JsonbBuilder.create();
    }

    @Override
    public ChatMessage decode(String s) throws DecodeException {
        return jsonb.fromJson(s, ChatMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void destroy() {
        try {
            jsonb.close();
        } catch (Exception ignored) {

        }
    }
}
