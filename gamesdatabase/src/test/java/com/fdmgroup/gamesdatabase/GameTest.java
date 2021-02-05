package com.fdmgroup.gamesdatabase;

import com.fdmgroup.gamesdatabase.model.Developer;
import com.fdmgroup.gamesdatabase.model.Game;
import com.fdmgroup.gamesdatabase.repository.DeveloperDao;
import com.fdmgroup.gamesdatabase.repository.GameDao;
import com.fdmgroup.gamesdatabase.repository.GameDao;
import com.fdmgroup.gamesdatabase.service.DeveloperService;
import com.fdmgroup.gamesdatabase.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private GameDao gameDao;
    private GameService gameService;


    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("gamesdatabase");
        entityManager = entityManagerFactory.createEntityManager();
        gameDao = new GameDao(entityManager);
        gameService = new GameService(gameDao);
        Developer developer1 = new Developer("Toby Fox", "Boston, Massachusetts, USA");
        Game gameToAdd = new Game("Undertale", developer1);
        gameService.create(gameToAdd);
    }


    @Test
    void GameCanBeCreated() {
        Developer developer1 = new Developer("CD Projekt Red", "Warsaw, Poland");
        Game game1 = new Game("Witcher 3", developer1);
        gameService.create(game1);
        assertTrue(game1.getGameId() > 0);
    }

    @Test
    void GameCanBeRetrievedFromDatabase_UsingId() {
        Game gameFromDb = gameService.retrieve(1);
        assertTrue(gameFromDb.getGameId() == 1);
    }

}
