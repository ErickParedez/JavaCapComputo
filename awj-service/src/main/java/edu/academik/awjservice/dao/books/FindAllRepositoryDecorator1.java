package edu.academik.awjservice.dao.books;

import java.util.List;

import edu.academik.awjservice.dto.BookDTO;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@Decorator
@Priority(3)
@Log
public class FindAllRepositoryDecorator1 implements FindAllRepository {

    @Inject
    @Delegate
    private FindAllRepository findAllRepository;

    @Override
    public List<BookDTO> findAll() {
        log.info("Estoy en decorador 1.");

        return this.findAllRepository.findAll().stream()
                .map(dto -> BookDTO.builder()
                        .bookId(dto.getBookId())
                        .isbn(dto.getIsbn())
                        .libraryName(dto.getLibraryName().toUpperCase())
                        .title(dto.getTitle().toUpperCase())
                        .price(dto.getPrice())
                        .build())
                .toList();
    }

}
