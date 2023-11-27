package com.placefinder.placefinder.repository;

import com.placefinder.placefinder.entity.Evaluation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EvaluationRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Evaluation evaluation) {
        em.persist(evaluation);
    }

    public Evaluation findOne(Long id) {
        return em.find(Evaluation.class, id);
    }

    public List<Evaluation> findByPlace(String placeType, Long placeId) {
        return em.createQuery("select e from Evaluation e where e.placeType = :placeType and e.placeId = :placeId", Evaluation.class)
            .setParameter("placeType", placeType)
            .setParameter("placeId", placeId)
            .getResultList();
    }

    public void delete(Long id) {
        Evaluation evaluation = em.find(Evaluation.class, id);
        if (evaluation != null) {
            em.remove(evaluation);
        }
    }

    public void deleteAllByUser(String username) {
        em.createQuery("delete from Evaluation e where e.username = :username")
            .setParameter("username", username)
            .executeUpdate();
    }
}
