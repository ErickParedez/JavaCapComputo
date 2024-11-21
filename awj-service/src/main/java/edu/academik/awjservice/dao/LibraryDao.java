package edu.academik.awjservice.dao;

import java.util.Optional;

import edu.academik.awjservice.domain.Library;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class LibraryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Library> findById(Long id) {
        return Optional.ofNullable(this.entityManager.find(Library.class, id));
    }

}
