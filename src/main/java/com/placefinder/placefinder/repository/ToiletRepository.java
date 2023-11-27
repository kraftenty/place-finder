package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.PublicFacility;
import com.placefinder.placefinder.entity.Toilet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    public List<Toilet> findToiletsWithinDistance(double latitude, double longitude, double distance) {
        String jpql = "SELECT t FROM Toilet t WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) * cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(t.latitude)))) < :distance ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) * cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(t.latitude))))";
        TypedQuery<Toilet> query = em.createQuery(jpql, Toilet.class);
        query.setParameter("latitude", latitude);
        query.setParameter("longitude", longitude);
        query.setParameter("distance", distance);
        return query.getResultList();
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
