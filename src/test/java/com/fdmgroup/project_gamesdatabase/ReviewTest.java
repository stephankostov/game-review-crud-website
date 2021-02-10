package com.fdmgroup.project_gamesdatabase;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.DeveloperService;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import com.fdmgroup.project_gamesdatabase.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;
    private User user;
    private Game game;
    private Review review;
    /*@Autowired
    private DeveloperService developerService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService; */

    @Test
    void ReviewCanBeCreated() {
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        //developerService.create(developer);
        user = new User("steph", "steph179@gmail.com", "pwrd");
        //userService.create(user);
        game = new Game("Undertale", developer);
        //gameService.create(game);
        review = new Review(user, game, 5, "rly good :^)");
        reviewService.create(review);
        assertTrue(review.getId() > 0);
    }
}
