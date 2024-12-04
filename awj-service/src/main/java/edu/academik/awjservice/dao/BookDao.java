package edu.academik.awjservice.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import edu.academik.awjservice.dao.books.FindAllRepository;
import edu.academik.awjservice.domain.Book;
import edu.academik.awjservice.domain.Library;
import edu.academik.awjservice.dto.BookDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class BookDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Inject
        private FindAllRepository findAllRepository;

        public List<BookDTO> findAll() {
                return this.findAllRepository.findAll();
        }

        public BookDTO findByIdAsDTO(Long id) {
                CriteriaBuilder builder = entityManager.getCriteriaBuilder();

                CriteriaQuery<Tuple> query = builder.createTupleQuery();

                Root<Book> root = query.from(Book.class);

                Join<Book, Library> libraryJoin = root.join("library");

                query.multiselect(
                                root.get("bookId").alias("bookId"),
                                root.get("title").alias("title"),
                                root.get("isbn").alias("isbn"),
                                root.get("price").alias("price"),
                                libraryJoin.get("name").alias("libraryName"))
                                .where(
                                                builder.equal(root.get("bookId"), id));

                return entityManager.createQuery(query).getResultStream()
                                .findFirst()
                                .map(tuple -> BookDTO.builder()
                                                .bookId(tuple.get("bookId", Long.class))
                                                .isbn(tuple.get("isbn", String.class))
                                                .libraryName(tuple.get("libraryName", String.class))
                                                .title(tuple.get("title", String.class))
                                                .price(tuple.get("price", BigDecimal.class))
                                                .build())
                                .orElse(null);
        }

        public void persist(Book book) {
                this.entityManager.persist(book);
        }

        public Optional<Book> findById(Long id) {
                return Optional.ofNullable(this.entityManager.find(Book.class, id));
        }

        public boolean existsIsbn(String isbn) {
                // select count(*) from book where ibsn = ?
                CriteriaBuilder builder = entityManager.getCriteriaBuilder();

                CriteriaQuery<Long> query = builder.createQuery(Long.class);

                Root<Book> root = query.from(Book.class);

                query.where(builder.equal(root.get("isbn"), isbn));

                query.select(builder.count(root));

                // count > 0 : true
                return this.entityManager.createQuery(query).getSingleResult()
                                .compareTo(0L) > 0;

        }

}
