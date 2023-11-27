package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    public List<Library> findLibrariesWithinDistance(double latitude, double longitude, double distance) {
        String jpql = "SELECT l FROM Library l WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(l.latitude)))) < :distance ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(l.latitude))))";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter("latitude", latitude);
        query.setParameter("longitude", longitude);
        query.setParameter("distance", distance);
        return query.getResultList();
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
