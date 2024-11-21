package edu.academik.awjservice.dao.books;

import java.math.BigDecimal;
import java.util.List;

import edu.academik.awjservice.domain.Book;
import edu.academik.awjservice.domain.Library;
import edu.academik.awjservice.dto.BookDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class FindAllRepositoryCocrete implements FindAllRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookDTO> findAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root<Book> root = query.from(Book.class);

        Join<Book, Library> libraryJoin = root.join("library");

        query.multiselect(
                root.get("bookId").alias("bookId"),
                root.get("title").alias("title"),
                root.get("isbn").alias("isbn"),
                root.get("price").alias("price"),
                libraryJoin.get("name").alias("libraryName"));

        return entityManager.createQuery(query).getResultStream().map(tuple -> BookDTO.builder()
                .bookId(tuple.get("bookId", Long.class))
                .isbn(tuple.get("isbn", String.class))
                .libraryName(tuple.get("libraryName", String.class))
                .title(tuple.get("title", String.class))
                .price(tuple.get("price", BigDecimal.class))
                .build()).toList();

    }
}
