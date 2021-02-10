package com.fdmgroup.project_gamesdatabase.reviewtests;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;
    private User user;
    private Game game;
    private Review review;

    @Test
    void ReviewCanBeCreated() {
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        user = new User("steph", "steph179@gmail.com", "pwrd");
        game = new Game("Undertale", developer);
        review = new Review(user, game, 5, "rly good :^)");
        reviewService.create(review);
        assertTrue(review.getId() > 0);
    }
}
