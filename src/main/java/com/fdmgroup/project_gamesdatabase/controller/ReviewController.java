package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import com.fdmgroup.project_gamesdatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping("/Game/ReviewGame/{gameId}")
    public ModelAndView reviewGame(@PathVariable("gameId")Long gameId) {
        ModelAndView model = new ModelAndView("../WEB-INF/gameReview.jsp");
        model.addObject("game", gameService.retrieve(gameId).get());
        model.addObject("allUsers", userService.retrieveAll());
        List<Integer> ratingOptions = new ArrayList<>();
        ratingOptions.add(1);
        ratingOptions.add(2);
        ratingOptions.add(3);
        ratingOptions.add(4);
        ratingOptions.add(5);
        model.addObject("ratingOptions", ratingOptions);
        return model;
    }

    /*
    @PostMapping("/ReviewGameSubmit")
    public ModelAndView reviewGameSubmit(@ModelAttribute("review") Review review) {
        reviewService.create(review);
        ModelAndView model = new ModelAndView("forward://WEB-INF/allGames.jsp");
        GameController.addAllGameRatingsToModel(model, gameService, reviewService);
        return model;
    } */


}
