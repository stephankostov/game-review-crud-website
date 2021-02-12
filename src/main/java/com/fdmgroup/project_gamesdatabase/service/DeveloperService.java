package com.fdmgroup.project_gamesdatabase.service;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.repository.DeveloperDao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    private static Logger LOGGER = LogManager.getLogger(DeveloperService.class);

    @Autowired
    private DeveloperDao developerDao;

    public DeveloperService(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    public void create(Developer developer) {
            developerDao.save(developer);
    }

    public Optional<Developer> retrieve(long developerId) {
        return developerDao.findById(developerId);
    }

    public List<Developer> retrieveAll() { return developerDao.findAll();
    }

    public void update(Developer developerFromDb) {
        Optional<Developer> developerToUpdate = developerDao.findById(developerFromDb.getDeveloperId());
        if (developerToUpdate.isPresent()) {
            developerDao.save(developerFromDb);
        } else {
            LOGGER.info("No such developer in database");
        }
    }

    public boolean delete(long developerId) {
        developerDao.deleteById(developerId);
        return true;
    }


}
