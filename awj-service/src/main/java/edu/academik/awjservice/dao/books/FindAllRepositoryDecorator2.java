package edu.academik.awjservice.dao.books;

import java.util.List;

import edu.academik.awjservice.dto.BookDTO;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@Decorator
@Priority(2)
@Log
public class FindAllRepositoryDecorator2 implements FindAllRepository {

    @Inject
    @Delegate
    private FindAllRepository findAllRepository;

    @Override
    public List<BookDTO> findAll() {

        log.info("Estoy en decorador 2.");

        return this.findAllRepository.findAll();
    }

}
