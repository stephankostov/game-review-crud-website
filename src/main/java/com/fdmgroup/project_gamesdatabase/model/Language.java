package com.fdmgroup.project_gamesdatabase.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_gen")
    @SequenceGenerator(name = "language_gen", sequenceName = "LANGUAGE_SEQ", allocationSize = 1)
    private long languageId;

    @Column
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return languageId == language.languageId &&
                Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, name);
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                '}';
    }
}
