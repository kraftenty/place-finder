package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Library;
import com.placefinder.placefinder.entity.PublicFacility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublicFacilityRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(PublicFacility publicFacility) {
        em.persist(publicFacility);
        return publicFacility.getId();
    }

    public void saveAll(List<PublicFacility> publicFacilities) {
        for (PublicFacility publicFacility : publicFacilities) {
            em.persist(publicFacility);
        }
    }

    public List<PublicFacility> findAll() {
        return em.createQuery("select p from PublicFacility p", PublicFacility.class)
            .getResultList();
    }

    public List<PublicFacility> findPublicFacilitiesWithinDistance(double latitude, double longitude, double distance) {
        String jpql = "SELECT p FROM PublicFacility p WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(p.latitude)))) < :distance ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(p.latitude))))";
        TypedQuery<PublicFacility> query = em.createQuery(jpql, PublicFacility.class);
        query.setParameter("latitude", latitude);
        query.setParameter("longitude", longitude);
        query.setParameter("distance", distance);
        return query.getResultList();
    }


    public PublicFacility findById(Long id) {
        return em.find(PublicFacility.class, id);
    }

    public void update(PublicFacility publicFacility) {
        em.flush();
    }

    public void delete(Long id) {
        PublicFacility publicFacility = em.find(PublicFacility.class, id);
        if (publicFacility != null) {
            em.remove(publicFacility);
        }
    }
}
