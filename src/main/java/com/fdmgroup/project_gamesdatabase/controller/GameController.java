package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/game/")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ReviewService reviewService;

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
        System.err.println("game.getGameId() : " + (gameService.retrieve(game.getGameId())));
        System.err.println("gameId : " + (gameService.retrieve(gameId)));
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

    @GetMapping("gameRatings")
    public ResponseEntity<Map<Game,OptionalDouble>> getGameRatings() {

        // 1
        Map<Game, OptionalDouble> averageRatingsAndGames = gameService.retrieveAll().stream()
                .collect(Collectors.toMap(Function.identity(),game -> game.getAvgRating()));
        return ResponseEntity.ok(averageRatingsAndGames);

    }
    /*

    // todo find out best practice for this;
    // look into steams
    public static void addAllGameRatingsToModel(ModelAndView modelAndView, GameService gameService, ReviewService reviewService) {
        List<Game> allGames = gameService.retrieveAll();
        HashMap<Game, Double> gameRatings = new HashMap<>();
        for (Game game : allGames) {
            gameRatings.put(game, reviewService.getAverageGameRating(game));
        }
        modelAndView.addObject("gameRatings", gameRatings);
    }

    @GetMapping("gameRatings")
    public ModelAndView allGameRatings(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("/WEB-INF/allGames.jsp");
        addAllGameRatingsToModel(modelAndView, gameService, reviewService);
        LOGGER.info("game ratings passed into model");
        return modelAndView;
    }
    */



}
