package com.fdmgroup.project_gamesdatabase_spring.jpa;

import com.fdmgroup.gamesdatabase.model.User;
import com.fdmgroup.gamesdatabase.repository.Dao;
import com.fdmgroup.gamesdatabase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenericDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private Dao<User> genericUserDao;
    private UserService userService;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("gamesdatabase");
        entityManager = entityManagerFactory.createEntityManager();
        genericUserDao = new Dao<User>(entityManager, User.class);
        userService = new UserService(genericUserDao);
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

    @Test
    void AListOfUsersCanBeRetrieved() {
        List<User> allUsers = userService.retrieveAll();
        assertTrue(allUsers.size() > 0);
    }

    @Test
    void UserCanBeUpdatedInDatabase() {
        User userFromDb = userService.retrieve(1);
        String nameBeforeUpdate = userFromDb.getUsername();
        userFromDb.setUsername("Stephan");
        userService.update(userFromDb);
        User updatedUser = userService.retrieve(1);
        String nameAfterUpdate = updatedUser.getUsername();
        assertFalse(nameBeforeUpdate.equals(nameAfterUpdate));
    }

    @Test
    void UserCanBeDeleted() {
        User userToDelete = userService.retrieve(1);
        long userId = userToDelete.getUserId();
        int numInDbBefore = userService.retrieveAll().size();
        userService.delete(userId);
        int numInDbAfter = userService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }

}
