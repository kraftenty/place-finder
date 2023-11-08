package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LibraryRepository {
    private EntityManager em;

    public void save(Library library) {
        em.persist(library);
    }

    public List<Library> findAll() {
        return em.createQuery("select l from Library l", Library.class)
            .getResultList();
    }

    /**
     * TODO
     * - findById, update, delete 추가
     */
}
