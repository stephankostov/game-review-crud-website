package com.fdmgroup.gamesdatabase.service;

import com.fdmgroup.gamesdatabase.repository.CRUD;

import java.util.List;

public class Service<T> {

    private CRUD<T> dao;

    public Service(CRUD<T> dao) {
        this.dao = dao;
    }

    public void create(T t) {
        dao.create(t);
    }

    public T retrieve(long id) {
        return dao.retrieve(id);
    }

    public List<T> retrieveAll() { return dao.retrieveAll();
    }

    public void update(T t) { dao.update(t);
    }

    public void delete(long id) { dao.delete(id);}


}
