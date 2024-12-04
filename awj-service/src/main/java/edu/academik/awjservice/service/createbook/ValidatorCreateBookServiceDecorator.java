package edu.academik.awjservice.service.createbook;

import java.util.logging.Level;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.dao.LibraryDao;
import edu.academik.awjservice.domain.Library;
import edu.academik.awjservice.request.CreateBookRequest;
import edu.academik.awjservice.response.CreateBookResponse;
import edu.academik.awjservice.service.CreateBookService;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@Decorator
@Priority(1)
@Log
public class ValidatorCreateBookServiceDecorator implements CreateBookService {

    @Inject
    @Delegate
    private CreateBookService createBookService;

    @Inject
    private BookDao bookDao;

    @Inject
    private LibraryDao libraryDao;

    @Override
    public CreateBookResponse create(CreateBookRequest request) {

        // Validaciones aquí
        if (bookDao.existsIsbn(request.getIsbn())) {
            throw new RuntimeException("ISBN existe (validación en decorador).");
        }

        Library library = this.libraryDao.findById(request.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Id de Biblioteca no existe."));

        request.setLibrary(library);

        var createBookResponse = this.createBookService.create(request);

        log.log(Level.INFO, "Libro ID Creado: {0}.", createBookResponse.getBookId());

        return createBookResponse;
    }

}
