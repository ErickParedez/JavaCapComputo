package edu.academik.awjservice.dao.books;

import java.util.List;

import edu.academik.awjservice.dto.BookDTO;

public interface FindAllRepository {
    List<BookDTO> findAll();
}
