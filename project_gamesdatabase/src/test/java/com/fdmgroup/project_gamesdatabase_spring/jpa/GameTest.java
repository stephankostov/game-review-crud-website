package com.fdmgroup.project_gamesdatabase_spring.jpa;

import com.fdmgroup.gamesdatabase.model.Developer;
import com.fdmgroup.gamesdatabase.model.Game;
import com.fdmgroup.gamesdatabase.repository.GameDao;
import com.fdmgroup.gamesdatabase.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void GameCanBeUpdatedInDatabase() {
        Game gameFromDb = gameService.retrieve(1);
        String nameBeforeUpdate = gameFromDb.getName();
        gameFromDb.setName("changedname");
        gameService.update(gameFromDb);
        Game updatedGame = gameService.retrieve(1);
        String nameAfterUpdate = updatedGame.getName();
        assertFalse(nameBeforeUpdate.equals(nameAfterUpdate));
    }

    @Test
    void AListOfGamesCanBeRetrieved() {
        List<Game> allGames = gameService.retrieveAll();
        assertTrue(allGames.size() > 0);
    }

    @Test
    void GameCanBeDeleted() {
        Game gameToDelete = gameService.retrieve(1);
        long gameId = gameToDelete.getGameId();
        int numInDbBefore = gameService.retrieveAll().size();
        gameService.delete(gameId);
        int numInDbAfter = gameService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }

}
