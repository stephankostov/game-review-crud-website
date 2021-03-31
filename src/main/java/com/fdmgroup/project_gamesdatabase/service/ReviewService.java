package com.fdmgroup.project_gamesdatabase.service;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.repository.ReviewDao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

@Service
public class ReviewService {

    private static Logger LOGGER = LogManager.getLogger(ReviewService.class);

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private GameService gameService;

    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public long create(Review review) {
        reviewDao.save(review);
        return review.getReviewId();
    }

    public Optional<Review> retrieve(long reviewId) {
        return reviewDao.findById(reviewId);
    }

    public List<Review> retrieveAll() { return reviewDao.findAll();
    }

    public Optional<Review> update(Review reviewFromDb) {
        Optional<Review> reviewToUpdate = reviewDao.findById(reviewFromDb.getReviewId());
        if (reviewToUpdate.isPresent()) {
            reviewDao.save(reviewFromDb);
        } else {
            LOGGER.info("No such review in database");
        }
        return reviewToUpdate;
    }

    public boolean delete(long reviewId) {
        reviewDao.deleteById(reviewId);
        return true;
    }

    public double getAverageGameRating(Long gameId){
        Game game = gameService.retrieve(gameId).get();
        Optional<Double> avgRating = reviewDao.getAverageGameRating(game);
        if (avgRating.isPresent()) {
            return avgRating.get();
        } else {
            return 0;
        }
    }

    public Optional<Review> getByUserAndGame(User user, Game game) {
        return reviewDao.getByUserAndGame(user, game);
    }
}

