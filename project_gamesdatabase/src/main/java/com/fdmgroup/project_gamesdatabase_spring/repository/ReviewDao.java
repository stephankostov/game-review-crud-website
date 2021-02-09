package com.fdmgroup.project_gamesdatabase_spring.repository;

import com.fdmgroup.project_gamesdatabase_spring.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Long> {
}
