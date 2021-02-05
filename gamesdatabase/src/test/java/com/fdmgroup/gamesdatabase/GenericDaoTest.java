package com.fdmgroup.gamesdatabase;

import com.fdmgroup.gamesdatabase.model.User;
import com.fdmgroup.gamesdatabase.repository.Dao;
import com.fdmgroup.gamesdatabase.repository.UserDao;
import com.fdmgroup.gamesdatabase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GenericDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private Dao<User> userDao;
    private UserService userService;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("gamesdatabase");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new Dao<>(entityManager, User.class);
        userService = new UserService(userDao);
        User userToAdd = new User("steph", "steph179@gmail.com", "pswd");
        userService.create(userToAdd);
    }

    @Test
    void UserCanBeCreated(){
        User user1 = new User("user1", "user@usermail.com", "userpassword");
        userService.create(user1);
        assertNotNull(user1);
    }

    @Test
    void UserCanBeRetrievedFromDatabase_UsingId() {
        User user1 = new User("user1", "user@usermail.com", "userpassword");
        userService.create(user1);
        User retrievedUser = userService.retrieve(user1.getUserId());
        assertEquals(retrievedUser, user1);
    }

}
