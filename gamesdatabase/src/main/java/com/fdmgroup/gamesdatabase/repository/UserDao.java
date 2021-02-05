package com.fdmgroup.gamesdatabase.repository;

import com.fdmgroup.gamesdatabase.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao implements CRUD<User>, GetUniqueIdentifier {

    private EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User retrieve(long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> retrieveAll() {
        return entityManager.createQuery("select u from User u order by u.username", User.class).getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long userId) {
        entityManager.getTransaction().begin();
        User authorFromDb = entityManager.find(User.class, userId);
        if (authorFromDb == null) {
        }else {
            entityManager.remove(authorFromDb);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public int retrieveByUniqueIdentifier(String email) {
        return entityManager.createQuery(
                "select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .size();
    }
}
