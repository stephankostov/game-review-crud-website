package com.fdmgroup.project_gamesdatabase.usertests;

import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserTest {

    @Autowired
    private UserService userService;
    private User userToAdd;
    private User userToAdd2;

    @BeforeEach()
    void setup() {
        userToAdd = new User("steph", "steph179@gmail.com", "pwrd");
        userService.create(userToAdd);
        userToAdd2 = new User("derg", "mackiechip@gmail.com", "password");
        userService.create(userToAdd2);
    }

    @Test
    void UserCanBeCreated(){
        User user1 = new User("user1", "user@usermail.com", "userpassword");
        userService.create(user1);
        assertTrue(user1.getUserId() > 0);
    }

    @Test
    void UserCanBeRetrievedFromDatabase_UsingId() {
        User userFromDb = userService.retrieve(2).get();
        assertTrue(userFromDb.getUserId() == 2);
    }

    @Test
    void UserCanBeUpdatedInDatabase() {
        User userFromDb = userService.retrieve(2).get();
        String nameBeforeUpdate = userFromDb.getUsername();
        userFromDb.setUsername("Updated Username");
        userService.update(userFromDb);
        User updatedUser = userService.retrieve(2).get();
        String nameAfterUpdate = updatedUser.getUsername();
        assertFalse(nameBeforeUpdate.equals(nameAfterUpdate));
    }

    @Test
    void AListOfUsersCanBeRetrieved() {
        List<User> allUsers = userService.retrieveAll();
        assertTrue(allUsers.size() > 0);
    }

    @Test
    void AUserCanBeRetrieved_UsingUsernameAndPassword(){
        User userFromDb = userService.getByUsernameAndPassword("testuser", "password").get();
        assertTrue(userFromDb.getUserId() > 0);
    }

    @Test
    void UserCanBeDeleted() {
        User userToDelete = userService.retrieve(3).get();
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
