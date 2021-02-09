package com.fdmgroup.project_gamesdatabase_spring;

import com.fdmgroup.project_gamesdatabase_spring.model.Developer;
import com.fdmgroup.project_gamesdatabase_spring.model.Game;
import com.fdmgroup.project_gamesdatabase_spring.model.Review;
import com.fdmgroup.project_gamesdatabase_spring.model.User;
import com.fdmgroup.project_gamesdatabase_spring.service.DeveloperService;
import com.fdmgroup.project_gamesdatabase_spring.service.GameService;
import com.fdmgroup.project_gamesdatabase_spring.service.ReviewService;
import com.fdmgroup.project_gamesdatabase_spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @Test
    void ReviewCanBeCreated() {
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        developerService.create(developer);
        User user = new User("steph", "steph179@gmail.com", "pwrd");
        userService.create(user);
        Game game = new Game("Undertale", developer);
        gameService.create(game);
        Review review = new Review(user, game, 5, "rly good :^)");
        reviewService.create(review);
        assertTrue(review.getId() > 0);
    }
}
