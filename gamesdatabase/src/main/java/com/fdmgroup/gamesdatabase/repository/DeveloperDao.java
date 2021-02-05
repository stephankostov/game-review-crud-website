package com.fdmgroup.gamesdatabase.repository;

import com.fdmgroup.gamesdatabase.model.Developer;

import javax.persistence.EntityManager;
import java.util.List;

public class DeveloperDao implements CRUD<Developer>, GetUniqueIdentifier {

    private EntityManager entityManager;

    public DeveloperDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Developer developer) {
        entityManager.getTransaction().begin();
        entityManager.persist(developer);
        entityManager.getTransaction().commit();
    }

    @Override
    public Developer retrieve(long developerId) {
        return entityManager.find(Developer.class, developerId);
    }

    @Override
    public List<Developer> retrieveAll() {
        return entityManager.createQuery("select u from Developer u order by u.name", Developer.class).getResultList();
    }

    @Override
    public void update(Developer developer) {
        entityManager.getTransaction().begin();
        entityManager.merge(developer);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long developerId) {
        entityManager.getTransaction().begin();
        Developer authorFromDb = entityManager.find(Developer.class, developerId);
        if (authorFromDb == null) {
        }else {
            entityManager.remove(authorFromDb);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public int retrieveByUniqueIdentifier(String email) {
        return 0;
    }
}



