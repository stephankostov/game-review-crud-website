package com.fdmgroup.project_gamesdatabase.reviewtests;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
       User user1 = new User("lump", "lds@gmail.com", "pwrd");
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
        assertTrue(review.getReviewId() > 0);
    }

    @Test
    void ReviewCanBeRetrievedFromDatabase_UsingId() {
        Review reviewFromDb = reviewService.retrieve(2).get();
        assertTrue(reviewFromDb.getReviewId() == 2);
    }

    /*
    @Test ReviewCanBeRetrievedFromDatabase_UsingUserAndGame() {
        User user = new User("steph", "steph179@gmail.com", "pwrd");
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        Game game = new Game("Undertale", developer);
        Review review1 = new Review(game, user, 3, "review...");
        reviewService.create(review1);
        Review reviewFromDb = reviewService.retrieve(user, game).get();
        assertTrue(reviewFromDb.getId() > 0);
    } */

    @Test
    void AListOfReviewsCanBeRetrieved() {
        List<Review> allReviews = reviewService.retrieveAll();
        assertTrue(allReviews.size() > 0);
    }

    @Test
    @Transactional
    void GetAverageRating_ByGameId() {
        Developer developer = new Developer("Riot Games", "California, USA");
        Game game = new Game("Undertale3", developer);
        User user1 = new User("Ed", "edstew@gmail.com", "pwrd");
        User user2 = new User("Dunkey", "donkey@gmail.com", "pwrd");
        Review review1 = new Review(game, user1, 5, "good solid game");
        Review review2 = new Review(game, user2, 1, "trash");
        reviewService.create(review1);
        reviewService.create(review2);
        double gameRating = reviewService.getAverageGameRating(game);
        assertTrue(gameRating == (review1.getRating()+review2.getRating())/2);
    }

    @Test
    void ReviewCanBeDeleted() {
        Review reviewToDelete = reviewService.retrieve(3).get();
        long reviewId = reviewToDelete.getReviewId();
        int numInDbBefore = reviewService.retrieveAll().size();
        reviewService.delete(reviewId);
        int numInDbAfter = reviewService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }

    @Test
    @Transactional
    void ReviewCanBeRetrievedBy_UserAndGame() {
        Developer developer = new Developer("Riot Games", "California, USA");
        Game game = new Game("Undertale3", developer);
        User user = new User("Ed", "edstew@gmail.com", "pwrd");
        Review review1 = new Review(game, user, 5, "good solid game");
        reviewService.create(review1);
        assertTrue(reviewService.getByUserAndGame(user, game).isPresent());
    }

    @Test
    @Transactional
    void RepeatedReviewsReplacePreviousReviewsInDatabase() {
        Developer developer = new Developer("Toby Fox", "Massachuesets, USA");
        Game game = new Game("Undertale", developer);
        User user = new User("test99", "test99@gmail.com", "pwrd");
        Review originalReview = new Review(game, user, 3, "review...");
        reviewService.create(originalReview);
        long originalReviewId = originalReview.getReviewId();
        Review updatedReview = new Review(game, user, 5, "review...");
        reviewService.create(updatedReview);
        assertTrue(reviewService.retrieve(originalReviewId).isEmpty());
    }

}