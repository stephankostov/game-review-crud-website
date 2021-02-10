package com.fdmgroup.project_gamesdatabase.repository;

import com.fdmgroup.project_gamesdatabase.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    List <User> getByEmail(String email);

}
