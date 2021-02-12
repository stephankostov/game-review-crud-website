package com.fdmgroup.project_gamesdatabase.service;

import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.repository.UserDao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(User user) {
        String email = user.getEmail();
        if (userDao.getByEmail(email).size() > 0) {
           LOGGER.info("User email already registered");
        } else {
            userDao.save(user);
       }
    }

    public Optional<User> retrieve(long userId) {
        return userDao.findById(userId);
    }

    public List<User> retrieveAll() { return userDao.findAll();
    }

    public void update(User userFromDb) {
        Optional<User> userToUpdate = userDao.findById(userFromDb.getUserId());
        if (userToUpdate.isPresent()) {
            userDao.save(userFromDb);
        } else {
            LOGGER.info("No such user in database");
        }
    }

    public boolean delete(long userId) {
        userDao.deleteById(userId);
        return true;
    }


    public Optional<User> getByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }
}
