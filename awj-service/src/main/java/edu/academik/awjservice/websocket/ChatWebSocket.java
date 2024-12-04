package edu.academik.awjservice.websocket;

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

@ServerEndpoint("/ws/chat")
@Log
public class ChatWebSocket {
    
    private static final Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);

        System.out.println("Nueva conexión: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Mensaje recibido de " + session.getId() + ": " + message);

        sessions.stream()
                .filter(Session::isOpen)
                .forEach(s -> {
                    try {
                        s.getBasicRemote().sendText("Cliente " + session.getId() + ": " + message);
                    } catch (IOException ex) {
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
