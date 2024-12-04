package edu.academik.awjservice.websocket;

import jakarta.enterprise.event.Observes;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.java.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

@ApplicationScoped
@ServerEndpoint("/ws/books/notifications")
@Log
public class BookNotificationWebSocket {

    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        log.log(Level.INFO, "Nueva conexión: {0}.", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.log(Level.INFO, "Conexión cerrada: {0}.", session.getId());
    }

    public void sendMessage(String message) {

        log.log(Level.INFO, "Enviando mensaje: Total de sesiones:  {0}.", sessions.size());

        sessions.stream()
                .filter(Session::isOpen)
                .forEach(s -> {
                    try {
                        s.getBasicRemote().sendText("Cliente " + s.getId() + ": " + message);
                    } catch (IOException ex) {
                        log.log(Level.SEVERE, "Error", ex);
                    }
                });
    }
}
