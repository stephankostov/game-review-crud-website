package com.fdmgroup.gamesdatabase.repository;

import javax.persistence.EntityManager;
import java.util.List;

public class Dao<T> implements CRUD<T> {

    private EntityManager entityManager;
    private Class<T> typeClass;

    public Dao(EntityManager entityManager, Class<T> typeClass) {
        this.entityManager = entityManager;
        this.typeClass = typeClass;
    }

    public void create(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    @Override
    public T retrieve(long id) {
        return entityManager.find(this.typeClass, id); //todo can't get runtime .class on generic type
    }

    @Override
    public List<T> retrieveAll() {
        String className = typeClass.getName();
        return entityManager.createQuery("select t from className t order by t.name", typeClass).getResultList();
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int retrieveByUniqueIdentifier(String t) {
        return 0;
    }


}
