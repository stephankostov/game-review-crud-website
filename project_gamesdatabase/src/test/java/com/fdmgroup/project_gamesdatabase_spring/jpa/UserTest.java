package com.fdmgroup.project_gamesdatabase_spring.jpa;

import com.fdmgroup.gamesdatabase.model.User;
import com.fdmgroup.gamesdatabase.repository.UserDao;
import com.fdmgroup.gamesdatabase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("gamesdatabase");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new UserDao(entityManager);
        userService = new UserService(userDao);
        User userToAdd = new User("steph", "steph179@gmail.com", "pwrd");
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
    void AListOfUsersCanBeRetrieved() {
        List<User> allUsers = userService.retrieveAll();
        assertTrue(allUsers.size() > 0);
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

    @Test
    void UserCannotBeAdded_IfEmailNotUnique() {
        User userShouldFailToAdd = new User("steph2", "steph179@gmail.com", "pwrd2");
        int sizeBeforeAdding = userService.retrieveAll().size();
        userService.create(userShouldFailToAdd);
        int sizeAfterAdding = userService.retrieveAll().size();
        assertEquals(sizeBeforeAdding, sizeAfterAdding);
    }
}
