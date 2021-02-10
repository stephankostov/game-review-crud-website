package com.fdmgroup.project_gamesdatabase.repository;

import com.fdmgroup.project_gamesdatabase.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Long> {
}
