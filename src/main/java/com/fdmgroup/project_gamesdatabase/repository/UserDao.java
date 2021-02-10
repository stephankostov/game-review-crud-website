package com.fdmgroup.project_gamesdatabase.repository;

import com.fdmgroup.project_gamesdatabase.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    List <User> getByEmail(String email);

    Optional<User> getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
