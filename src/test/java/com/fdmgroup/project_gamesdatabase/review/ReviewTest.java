package com.fdmgroup.project_gamesdatabase.review;

import com.fdmgroup.project_gamesdatabase.model.*;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
       Game gameMock = mock(Game.class);
       User userMock = mock(User.class);
       Review review1 = new Review(gameMock, userMock, 5, "rly good :^)");
       reviewService.create(review1);
       System.err.println(reviewService.retrieveAll());
    }

    @Test
    void ReviewCanBeCreated() {
        Game gameMock = mock(Game.class);
        User userMock = mock(User.class);
        Review review = new Review(gameMock, userMock, 5, "rly good :^)");
        reviewService.create(review);
        assertTrue(review.getReviewId() > 0);
    }

    @Test
    void ReviewCanBeRetrievedFromDatabase_UsingId() {
        Review reviewFromDb = reviewService.retrieve(1).get();
        assertTrue(reviewFromDb.getReviewId() == 1);
    }

    @Test
    void AListOfReviewsCanBeRetrieved() {
        List<Review> allReviews = reviewService.retrieveAll();
        assertTrue(allReviews.size() > 0);
    }

    @Test
    void GameCanBeUpdatedInDatabase() {
        Review originalReview = reviewService.retrieve(1).get();
        String reviewBeforeUpdate = originalReview.getReview();
        originalReview.setReview("updatedreview");
        reviewService.update(originalReview);
        Review updateReview = reviewService.retrieve(1).get();
        String reviewAfterUpdate = updateReview.getReview();
        assertFalse(reviewBeforeUpdate.equals(reviewAfterUpdate));
    }

    @Test
    @Transactional
    void GetAverageRating_ByGame() {
        Language language = new Language("Python");
        Developer developer = new Developer("Riot Games", "California, USA");
        Game game = new Game("League of Legends", developer, language);
        User user1 = new User("Ed", "edstew@gmail.com", "pwrd");
        User user2 = new User("Dunkey", "donkey@gmail.com", "pwrd");
        Review review1 = new Review(game, user1, 5, "good solid game");
        Review review2 = new Review(game, user2, 1, ":,(");
        reviewService.create(review1);
        reviewService.create(review2);
        double gameRating = reviewService.getAverageGameRating(game.getGameId());
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
        Language language = new Language("Python");
        Game game = new Game("League of Legends", developer, language);
        User user = new User("Ed", "edstew@gmail.com", "pwrd");
        Review review1 = new Review(game, user, 5, "good solid game");
        reviewService.create(review1);
        assertTrue(reviewService.getByUserAndGame(user, game).isPresent());
    }

    @Test
    @Transactional
    void RepeatedReviewsReplacePreviousReviewsInDatabase() {
        Developer developer = new Developer("Riot Games", "California, USA");
        Language language = new Language("Python");
        Game game = new Game("League of Legends", developer, language);
        User user = new User("Ed", "edstew@gmail.com", "pwrd");
        Review originalReview = new Review(game, user, 3, "Not great");
        reviewService.create(originalReview);
        long originalReviewId = originalReview.getReviewId();
        Review updatedReview = new Review(game, user, 5, "Changed my mind");
        reviewService.create(updatedReview);
        assertTrue(reviewService.retrieve(originalReviewId).isEmpty());
    }

}