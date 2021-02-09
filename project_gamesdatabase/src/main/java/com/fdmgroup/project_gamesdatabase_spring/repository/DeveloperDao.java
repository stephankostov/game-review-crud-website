package com.fdmgroup.project_gamesdatabase_spring.repository;

import com.fdmgroup.project_gamesdatabase_spring.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperDao extends JpaRepository<Developer, Long> {

}