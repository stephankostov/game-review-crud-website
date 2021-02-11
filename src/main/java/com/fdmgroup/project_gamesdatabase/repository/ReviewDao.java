package com.fdmgroup.project_gamesdatabase.repository;

import com.fdmgroup.project_gamesdatabase.model.Game;
import com.fdmgroup.project_gamesdatabase.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDao extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(rating) FROM Review WHERE game=:game")
    double getAverageGameRating(@Param("game") Game game);

}
