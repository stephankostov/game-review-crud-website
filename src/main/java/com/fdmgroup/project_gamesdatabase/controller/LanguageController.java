package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Language;
import com.fdmgroup.project_gamesdatabase.service.LanguageService;
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
@RequestMapping("/api/languages/")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("get/{languageId}")
    public ResponseEntity<Language> getLanguage(@PathVariable("languageId") Long languageId) {
        Optional<Language> language = languageService.retrieve(languageId);
        if (language.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(language.get());
    }

    @GetMapping("all")
    public ResponseEntity<List<Language>> allLanguages() {
        List<Language> allLanguages = languageService.retrieveAll();
        return ResponseEntity.ok(allLanguages);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createLanguage(@RequestBody Language language) {
        Long languageId = languageService.create(language);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(languageId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("id") Long languageId,
                                                     @RequestBody Language language) {
        Optional<Language> updatedLanguage = languageService.update(language);
        if (updatedLanguage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLanguage.get());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteLanguage(@PathVariable("id") long id) {
        languageService.delete(id);
        LOGGER.info("Language deleted");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

    