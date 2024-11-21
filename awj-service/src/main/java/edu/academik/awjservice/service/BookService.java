package edu.academik.awjservice.service;

import java.util.List;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.dto.BookDTO;
import edu.academik.awjservice.interceptor.AWJLog;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@AWJLog
public class BookService {

    @Inject
    private BookDao bookDao;

    public List<BookDTO> findAll() {
        return this.bookDao.findAll();
    }

    public BookDTO findByIdAsDTO(Long id) {
        return this.bookDao.findByIdAsDTO(id);
    }
}
