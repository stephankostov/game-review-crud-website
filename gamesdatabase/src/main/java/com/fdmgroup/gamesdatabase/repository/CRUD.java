package com.fdmgroup.gamesdatabase.repository;

import com.fdmgroup.gamesdatabase.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface CRUD<T> {

    default void create(T t) {
    }

    T retrieve(long id);

    List<T> retrieveAll();

    void update(T t);

    void delete(long id);

    int retrieveByUniqueIdentifier(String t);

}
