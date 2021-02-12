package com.fdmgroup.project_gamesdatabase.service;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.repository.GameDao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static Logger LOGGER = LogManager.getLogger(GameService.class);

    @Autowired
    private GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void create(Game game) {
        gameDao.save(game);
    }

    public Optional<Game> retrieve(long gameId) {
        return gameDao.findById(gameId);
    }

    public List<Game> retrieveAll() { return gameDao.findAll();
    }

    public void update(Game gameFromDb) {
        Optional<Game> gameToUpdate = gameDao.findById(gameFromDb.getGameId());
        if (gameToUpdate.isPresent()) {
            gameDao.save(gameFromDb);
        } else {
            LOGGER.info("No such game in database");
        }
    }

    public boolean delete(long gameId) {
        gameDao.deleteById(gameId);
        return true;
    }


}
