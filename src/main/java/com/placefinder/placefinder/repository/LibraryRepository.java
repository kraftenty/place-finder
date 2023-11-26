package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LibraryRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Library library) {
        em.persist(library);
        return library.getId();
    }

    public void saveAll(List<Library> libraries) {
        for (Library library : libraries) {
            em.persist(library);
        }
    }

    public List<Library> findAll() {
        return em.createQuery("select l from Library l", Library.class)
            .getResultList();
    }

    public Library findById(Long id) {
        return em.find(Library.class, id);
    }

    public void update(Library library) {
        em.flush();
    }

    public void delete(Long id) {
        Library library = em.find(Library.class, id);
        if (library != null) {
            em.remove(library);
        }
    }

}
