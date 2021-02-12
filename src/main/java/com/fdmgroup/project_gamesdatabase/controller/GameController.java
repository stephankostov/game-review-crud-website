package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ReviewService reviewService;

    private static final Logger LOGGER = LogManager.getLogger(GameController.class);

    @GetMapping({"", "/home", "Home"})
    public ModelAndView allGameRatings() {
        ModelAndView model =  new ModelAndView("WEB-INF/home.jsp");
        List<Game> allGames = gameService.retrieveAll();
        //HashMap<Game, Optional<Double>> gameRatings = new HashMap<>();
        HashMap<Game, Double> gameRatings = new HashMap<>();
        for (Game game : allGames) {
            gameRatings.put(game, reviewService.getAverageGameRating(game));
        }
        model.addObject("gameRatings", gameRatings);
        LOGGER.info("game ratings passed into model");
        return model;
    }

    @GetMapping("{gameId}")
    public ModelAndView gameDetails(@PathVariable("gameId")Long gameId, Model model) {
        return new ModelAndView("/WEB-INF/gameDetails.jsp", "game", gameService.retrieve(gameId));
    }
}
