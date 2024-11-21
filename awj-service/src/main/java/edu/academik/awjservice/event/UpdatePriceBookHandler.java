package edu.academik.awjservice.event;

import java.util.logging.Level;

import edu.academik.awjservice.dao.BookDao;
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

    public void notifyUpdate(@Observes(during = TransactionPhase.AFTER_SUCCESS) BookUpdated bookUpdated) {

        bookDao.findById(bookUpdated.getBookId()).ifPresent(book -> {

            log.log(Level.INFO, "Libro actualizado:  {0}.", book);

        });

    }

}
