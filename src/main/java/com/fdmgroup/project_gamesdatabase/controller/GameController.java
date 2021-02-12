package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ReviewService reviewService;

    private static final Logger LOGGER = LogManager.getLogger(GameController.class);

    public static void addAllGameRatingsToModel(ModelAndView modelAndView, GameService gameService, ReviewService reviewService) {
        List<Game> allGames = gameService.retrieveAll();
        HashMap<Game, Double> gameRatings = new HashMap<>();
        for (Game game : allGames) {
            gameRatings.put(game, reviewService.getAverageGameRating(game));
        }
        modelAndView.addObject("gameRatings", gameRatings);
    }

    @GetMapping("/AllGames")
    public ModelAndView allGameRatings(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("/WEB-INF/allGames.jsp");
        addAllGameRatingsToModel(modelAndView, gameService, reviewService);
        LOGGER.info("game ratings passed into model");
        return modelAndView;
    }


    @GetMapping("/Game/{gameId}")
    public ModelAndView gameDetails(@PathVariable("gameId")Long gameId) {
        ModelAndView model = new ModelAndView("/WEB-INF/gameDetails.jsp");
        model.addObject("game", gameService.retrieve(gameId).get());
        return model;
    }



}
