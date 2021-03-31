package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/games/")
public class GameController {

    @Autowired
    private GameService gameService;

    private static final Logger LOGGER = LogManager.getLogger(GameController.class);

    @GetMapping("get/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable("gameId")Long gameId) {
        Optional<Game> game = gameService.retrieve(gameId);
        if (game.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game.get());
    }

    @GetMapping("all")
    public ResponseEntity<List<Game>> allGames() {
        List<Game> allGames = gameService.retrieveAll();
        return ResponseEntity.ok(allGames);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        Long gameId = gameService.create(game);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(gameId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable("id") Long gameId,
                                           @RequestBody Game game) {
        Optional<Game> updatedGame = gameService.update(game);
        if (updatedGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame.get());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable("id") long id){
        gameService.delete(id);
        LOGGER.info("Game deleted");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
