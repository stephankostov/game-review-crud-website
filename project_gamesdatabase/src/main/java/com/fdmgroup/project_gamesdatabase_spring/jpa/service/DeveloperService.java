package com.fdmgroup.project_gamesdatabase_jpa.service;

import com.fdmgroup.gamesdatabase.model.Developer;
import com.fdmgroup.gamesdatabase.repository.CRUD;
import com.fdmgroup.gamesdatabase.repository.DeveloperDao;

import java.util.List;

public class DeveloperService {
    
    private CRUD<Developer> developerDao;
    
    public DeveloperService(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    public void create(Developer developer) {
        developerDao.create(developer);
    }

    public Developer retrieve(long developerId) {
        return developerDao.retrieve(developerId);
    }

    public List<Developer> retrieveAll() { return developerDao.retrieveAll();
    }

    public void update(Developer developerFromDb) { developerDao.update(developerFromDb);
    }

    public void delete(long developerId) { developerDao.delete(developerId);}
}
