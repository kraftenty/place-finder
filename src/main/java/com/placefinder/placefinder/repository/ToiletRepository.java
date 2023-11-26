package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Toilet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToiletRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Toilet toilet) {
        em.persist(toilet);
        return toilet.getId();
    }

    public void saveAll(List<Toilet> toilets) {
        for (Toilet toilet : toilets) {
            em.persist(toilet);
        }
    }

    public List<Toilet> findAll() {
        return em.createQuery("select t from Toilet t", Toilet.class)
            .getResultList();
    }

    public Toilet findById(Long id) {
        return em.find(Toilet.class, id);
    }

    public void update(Toilet toilet) {
        em.flush();
    }

    public void delete(Long id) {
        Toilet toilet = em.find(Toilet.class, id);
        if (toilet != null) {
            em.remove(toilet);
        }
    }
}
