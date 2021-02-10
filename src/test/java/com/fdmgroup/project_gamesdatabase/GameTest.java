package com.fdmgroup.project_gamesdatabase;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.service.GameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameTest {

    @Autowired
    private GameService gameService;
    private Game game1;
    private Game game2;

    @BeforeEach
    void setup() {
        Developer developer1 = new Developer("Toby Fox", "Massachuesets, USA");
        game1 = new Game("Undertale", developer1);
        gameService.create(game1);
        Developer developer2 = new Developer("CD Projekt Red", "Warsaw, Poland");
        game2 = new Game("Witcher 3", developer2);
        gameService.create(game2);
        System.err.println(game2.getId());
        System.err.println(gameService.retrieveAll());
    }

    @Test
    void GameCanBeCreated() {
        Developer developer1 = new Developer("CD Projekt Red", "Warsaw, Poland");
        Game game1 = new Game("Witcher 3", developer1);
        gameService.create(game1);
        assertTrue(game1.getId() > 0);
    }

    @Test
    void GameCanBeRetrievedFromDatabase_UsingId() {
        assertTrue(gameService.retrieve(1).isPresent());
    }

    @Test
    void AListOfGamesCanBeRetrieved() {
        List<Game> allGames = gameService.retrieveAll();
        assertTrue(allGames.size() > 0);
    }

    @Test
    void GameCanBeUpdatedInDatabase() {
        Game gameFromDb = gameService.retrieve(1).get();
        String nameBeforeUpdate = gameFromDb.getName();
        gameFromDb.setName("Updated Name");
        gameService.update(gameFromDb);
        Game updatedGame = gameService.retrieve(1).get();
        String nameAfterUpdate = updatedGame.getName();
        assertFalse(nameBeforeUpdate.equals(nameAfterUpdate));
    }

    @Test
    void GameCanBeDeleted() {
        int numInDbBefore = gameService.retrieveAll().size();
        gameService.delete(2);
        int numInDbAfter = gameService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }

}
