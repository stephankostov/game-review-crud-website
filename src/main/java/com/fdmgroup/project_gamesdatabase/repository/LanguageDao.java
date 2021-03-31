package com.fdmgroup.project_gamesdatabase.repository;

import com.fdmgroup.project_gamesdatabase.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language, Long> {
}
