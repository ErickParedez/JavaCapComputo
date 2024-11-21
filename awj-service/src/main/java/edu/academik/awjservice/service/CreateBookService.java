package edu.academik.awjservice.service;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.dao.LibraryDao;
import edu.academik.awjservice.domain.Book;
import edu.academik.awjservice.domain.Library;
import edu.academik.awjservice.request.CreateBookRequest;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

@Singleton
public class CreateBookService {

    @Inject
    private LibraryDao libraryDao;

    @Inject
    private BookDao bookDao;

    public void create(CreateBookRequest request) {

        Library library = this.libraryDao.findById(request.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Id de Biblioteca no existe."));

        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setLibrary(library);

        this.bookDao.persist(book);
    }
}
