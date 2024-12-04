package edu.academik.awjservice.event;

import java.util.logging.Level;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.websocket.BookNotificationWebSocket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class UpdatePriceBookHandler {

    @Inject
    private BookDao bookDao;

    @Inject
    private BookNotificationWebSocket bookNotificationWebSocket;

    public void notifyUpdate(@Observes(during = TransactionPhase.AFTER_SUCCESS) BookUpdated bookUpdated) {

        bookDao.findById(bookUpdated.getBookId()).ifPresent(book -> {

            log.log(Level.INFO, "Libro actualizado:  {0}.", book);

            bookNotificationWebSocket.sendMessage(
                    String.format("Libro Actualizado %d", book.getBookId()));
        });
    }
}
