package com.fdmgroup.gamesdatabase.service;

import com.fdmgroup.gamesdatabase.model.Game;
import com.fdmgroup.gamesdatabase.repository.CRUD;

import java.util.List;

public class GameService {

    private CRUD<Game> gameDao;

    public GameService(CRUD<Game> gameDao) {
        this.gameDao = gameDao;
    }

    public void create(Game game) {
        gameDao.create(game);
    }

    public Game retrieve(long gameId) {
        return gameDao.retrieve(gameId);
    }

    public List<Game> retrieveAll() { return gameDao.retrieveAll();
    }

    public void update(Game gameFromDb) { gameDao.update(gameFromDb);
    }

    public void delete(long gameId) { gameDao.delete(gameId);}


}

