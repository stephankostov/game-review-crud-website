package com.fdmgroup.project_gamesdatabase.reviewtests;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.GameService;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        Game gameMock = mock(Game.class);
        User user1 = new User("steph", "steph179@gmail.com", "pwrd");
        Review review1 = new Review(gameMock, user1, 5, "rly good :^)");
        reviewService.create(review1);
        System.err.println(reviewService.retrieveAll());
    }

    @Test
    void ReviewCanBeCreated() {
        Game gameMock = mock(Game.class);
        User user1 = new User("steph", "steph179@gmail.com", "pwrd");
        Review review = new Review(gameMock, user1, 5, "rly good :^)");
        reviewService.create(review);
        assertTrue(review.getId() > 0);
    }



    @Test
    @Transactional
    void GetAverageRating_ByGameId() {
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        User user1 = new User("steph", "steph179@gmail.com", "pwrd");
        User user2 = new User("chris", "drysale@gmail.com", "pwrd");
        Game game = new Game("Undertale", developer);
        Review review1 = new Review(game, user1, 5, "rly good :^)");
        Review review2 = new Review(game, user2, 5, "great!!!!!");
        reviewService.create(review1);
        reviewService.create(review2);
        double gameRating = reviewService.getAverageGameRating(game);
        assertTrue(gameRating == (review1.getRating()+review2.getRating())/2);
    }
}
