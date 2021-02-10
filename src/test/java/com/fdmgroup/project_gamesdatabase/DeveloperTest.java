package com.fdmgroup.project_gamesdatabase;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.service.DeveloperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeveloperTest {

    @Autowired
    private DeveloperService developerService;
    private Developer developerToAdd;
    private Developer developerToAdd2;

    @BeforeEach
    void setup() {
        developerToAdd = new Developer("Toby Fox", "Boston, Massachusetts, USA");
        developerService.create(developerToAdd);
        developerToAdd2 = new Developer("CD Projekt Red", "Warsaw, Poland");
        developerService.create(developerToAdd2);
    }

    @Test
    void DeveloperCanBeCreated(){
        Developer developer1 = new Developer("Riot Games", "LA, California, USA");
        developerService.create(developer1);
        assertNotNull(developer1.getId() > 0);
    }

    @Test
    void DeveloperCanBeRetrievedFromDatabase_UsingId() {
        Developer developer1 = new Developer("Riot Games", "LA, California, USA");
        developerService.create(developer1);
        Developer retrievedDeveloper = developerService.retrieve(3).get();
        assertEquals(retrievedDeveloper.getName(), developer1.getName());
    }

    @Test
    void DeveloperCanBeUpdatedInDatabase() {
        Developer developerFromDb = developerService.retrieve(1).get();
        String nameBeforeUpdate = developerFromDb.getName();
        developerFromDb.setName("Fox Dev");
        developerService.update(developerFromDb);
        Developer updatedDeveloper = developerService.retrieve(1).get();
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
        Developer developerToDelete = developerService.retrieve(1).get();
        long developerId = developerToDelete.getId();
        int numInDbBefore = developerService.retrieveAll().size();
        developerService.delete(developerId);
        int numInDbAfter = developerService.retrieveAll().size();
        assertNotEquals(numInDbBefore, numInDbAfter);
    }

}
