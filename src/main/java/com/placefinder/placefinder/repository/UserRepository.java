package com.placefinder.placefinder.repository;


import com.placefinder.placefinder.entity.User;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
            .setParameter("name", name)
            .getResultList();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
            .getResultList();
    }

    public void update(User user) {
        em.flush();
    }

    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }


}
