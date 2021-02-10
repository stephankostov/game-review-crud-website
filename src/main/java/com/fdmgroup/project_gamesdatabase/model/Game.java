package com.fdmgroup.project_gamesdatabase.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_gen")
    @SequenceGenerator(name = "game_gen", sequenceName = "GAME_SEQ", allocationSize = 1)
    private long id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_DEV_ID")
    private Developer developer;

    public Game(String name, Developer developer) {
        this.name = name;
        this.developer = developer;
    }

    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long gameId) {
        this.id = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && name.equals(game.name) && developer.equals(game.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, developer);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developer=" + developer +
                '}';
    }
}



