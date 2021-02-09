package com.fdmgroup.project_gamesdatabase_spring.repository;

import com.fdmgroup.project_gamesdatabase_spring.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDao extends JpaRepository<Game, Long> {

}