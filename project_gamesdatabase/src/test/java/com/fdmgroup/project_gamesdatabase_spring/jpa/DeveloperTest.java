package com.fdmgroup.project_gamesdatabase_spring.jpa;

import com.fdmgroup.gamesdatabase.model.Developer;
import com.fdmgroup.gamesdatabase.repository.DeveloperDao;
import com.fdmgroup.gamesdatabase.service.DeveloperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeveloperTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private DeveloperDao developerDao;
    private DeveloperService developerService;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("gamesdatabase");
        entityManager = entityManagerFactory.createEntityManager();
        developerDao = new DeveloperDao(entityManager);
        developerService = new DeveloperService(developerDao);
        Developer developerToAdd = new Developer("Toby Fox", "Boston, Massachusetts, USA");
        developerService.create(developerToAdd);
    }

    @Test
    void DeveloperCanBeCreated(){
        Developer developer1 = new Developer("CD Projekt Red", "Warsaw, Poland");
        developerService.create(developer1); //todo how do I tes create database without testing retrieve at the same time?
        assertNotNull(developer1);
    }

    @Test
    void DeveloperCanBeRetrievedFromDatabase_UsingId() {
        Developer developerFromDb = developerService.retrieve(1);
        assertTrue(developerFromDb.getId() == 1);
    }

    @Test
    void DeveloperCanBeUpdatedInDatabase() {
        Developer developerFromDb = developerService.retrieve(1);
        String nameBeforeUpdate = developerFromDb.getName();
        developerFromDb.setName("Stephan");
        developerService.update(developerFromDb);
        Developer updatedDeveloper = developerService.retrieve(1);
        String nameAfterUpdate = updatedDeveloper.getName();
        assertFalse(nameBeforeUpdate.equals(nameAfterUpdate));
    }

    @Test
    void AListOfDevelopersCanBeRetrieved() {
        List<Developer> allDevelopers = developerService.retrieveAll();
        assertTrue(allDevelopers.size() > 0);
    }

    @Test
    void DeveloperCanBeDeleted() {
        Developer developerToDelete = developerService.retrieve(1);
        long developerId = developerToDelete.getId();
        int numInDbBefore = developerService.retrieveAll().size();
        developerService.delete(developerId);
        int numInDbAfter = developerService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }
}
