package com.fdmgroup.project_gamesdatabase.service;

import com.fdmgroup.project_gamesdatabase.model.Language;
import com.fdmgroup.project_gamesdatabase.repository.LanguageDao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    private static Logger LOGGER = LogManager.getLogger(LanguageService.class);

    @Autowired
    private LanguageDao languageDao;

    public LanguageService(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    public long create(Language language) {
        languageDao.save(language);
        return language.getLanguageId();
    }

    public Optional<Language> retrieve(long languageId) {
        return languageDao.findById(languageId);
    }

    public List<Language> retrieveAll() { return languageDao.findAll();
    }

    public Optional<Language> update(Language languageFromDb) {
        Optional<Language> languageToUpdate = languageDao.findById(languageFromDb.getLanguageId());
        if (languageToUpdate.isPresent()) {
            languageDao.save(languageFromDb);
        } else {
            LOGGER.info("No such language in database");
        } return languageToUpdate;
    }

    public boolean delete(long languageId) {
        languageDao.deleteById(languageId);
        return true;
    }


}

