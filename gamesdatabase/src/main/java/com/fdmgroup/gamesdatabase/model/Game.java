package com.fdmgroup.gamesdatabase.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Game {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "game_gen", sequenceName = "GAME_SEQ", allocationSize = 1)
    private long gameId;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "developerId")
    private Developer developer;

    public Game(String name, Developer developer) {
        this.name = name;
        this.developer = developer;
    }

    public Game() {
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
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
        return gameId == game.gameId && name.equals(game.name) && developer.equals(game.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name, developer);
    }
}



