package com.fdmgroup.gamesdatabase.repository;

import com.fdmgroup.gamesdatabase.model.Game;

import javax.persistence.EntityManager;
import java.util.List;

public class GameDao implements CRUD<Game>, GetUniqueIdentifier {

    private EntityManager entityManager;

    public GameDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Game game) {
        entityManager.getTransaction().begin();
        entityManager.persist(game);
        entityManager.getTransaction().commit();
    }

    @Override
    public Game retrieve(long gameId) {
        return entityManager.find(Game.class, gameId);
    }

    @Override
    public List<Game> retrieveAll() {
        return entityManager.createQuery("select g from Game g order by g.name", Game.class).getResultList();
    }

    @Override
    public void update(Game game) {
        entityManager.getTransaction().begin();
        entityManager.merge(game);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long gameId) {
        entityManager.getTransaction().begin();
        Game authorFromDb = entityManager.find(Game.class, gameId);
        if (authorFromDb == null) {
        } else {
            entityManager.remove(authorFromDb);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public int retrieveByUniqueIdentifier(String t) {
        return 0;
    }
}