package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Developer;
import com.fdmgroup.project_gamesdatabase.service.DeveloperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/developers/")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("get/{developerId}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable("developerId") Long developerId) {
        Optional<Developer> developer = developerService.retrieve(developerId);
        if (developer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(developer.get());
    }

    @GetMapping("all")
    public ResponseEntity<List<Developer>> allDevelopers() {
        List<Developer> allDevelopers = developerService.retrieveAll();
        return ResponseEntity.ok(allDevelopers);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createDeveloper(@RequestBody Developer developer) {
        Long developerId = developerService.create(developer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(developerId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable("id") Long developerId,
                                                @RequestBody Developer developer) {
        Optional<Developer> updatedDeveloper = developerService.update(developer);
        if (updatedDeveloper.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDeveloper.get());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteDeveloper(@PathVariable("id") long id) {
        developerService.delete(id);
        LOGGER.info("Developer deleted");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

    