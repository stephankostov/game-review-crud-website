package com.fdmgroup.project_gamesdatabase_jpa.service;

import com.fdmgroup.gamesdatabase.model.User;
import com.fdmgroup.gamesdatabase.repository.CRUD;

import java.util.List;

public class UserService {

    private CRUD<User> userDao;

    public UserService(CRUD<User> userDao) {
        this.userDao = userDao;
    }

    public void create(User user) {
        String email = user.getEmail();
        if (userDao.retrieveByUniqueIdentifier(email) > 0) {
           System.out.println("User already registered with this email");
        } else { userDao.create(user);
       }
    }

    public User retrieve(long userId) {
        return userDao.retrieve(userId);
    }

    public List<User> retrieveAll() { return userDao.retrieveAll();
    }

    public void update(User userFromDb) { userDao.update(userFromDb);
    }

    public void delete(long userId) { userDao.delete(userId);}


}
