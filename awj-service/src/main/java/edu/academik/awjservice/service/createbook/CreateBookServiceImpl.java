package edu.academik.awjservice.service.createbook;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.domain.Book;
import edu.academik.awjservice.request.CreateBookRequest;
import edu.academik.awjservice.response.CreateBookResponse;
import edu.academik.awjservice.service.CreateBookService;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

@Singleton
public class CreateBookServiceImpl implements CreateBookService {

    @Inject
    private BookDao bookDao;

    public CreateBookResponse create(CreateBookRequest request) {

        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setLibrary(request.getLibrary());

        this.bookDao.persist(book);

        return CreateBookResponse.builder()
                .bookId(book.getBookId())
                .build();
    }
}
