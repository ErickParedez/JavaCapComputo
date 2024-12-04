package edu.academik.awjservice.websocket;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import edu.academik.awjservice.websocket.util.ChatMessage;
import edu.academik.awjservice.websocket.util.MessageDecoder;
import edu.academik.awjservice.websocket.util.MessageEncoder;

@Log
@ServerEndpoint(value = "/ws/jsonchat", decoders = { MessageDecoder.class }, encoders = { MessageEncoder.class })
public class JsonChatWebSocket {

    private static final Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Nueva conexión: " + session.getId());
    }

    @OnMessage
    public void onMessage(ChatMessage message, Session session) {
        System.out.println("Mensaje recibido de " + message.getUsername() + ": " + message.getMessage());

        sessions.stream()
                .filter(Session::isOpen)
                .forEach(s -> {
                    try {
                        s.getBasicRemote().sendObject(message);
                    } catch (IOException | EncodeException ex) {
                        log.log(Level.SEVERE, "Error", ex);
                    }
                });
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Conexión cerrada: " + session.getId());
    }
}
