package com.fdmgroup.project_gamesdatabase_spring.repository;

import com.fdmgroup.project_gamesdatabase_spring.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    List <User> getByEmail(String email);

}
