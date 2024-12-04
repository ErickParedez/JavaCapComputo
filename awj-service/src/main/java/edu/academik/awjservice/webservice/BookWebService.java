package edu.academik.awjservice.webservice;

import java.util.List;

import edu.academik.awjservice.dto.BookDTO;
import edu.academik.awjservice.service.BookService;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "BooksWS")
public class BookWebService {

    @Inject
    private BookService bookService;

    @WebMethod(operationName = "FindBooks")
    public List<BookDTO> find() {
        return this.bookService.findAll();
    }

}
