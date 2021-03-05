package com.fdmgroup.project_gamesdatabase.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_gen")
    @SequenceGenerator(name = "game_gen", sequenceName = "GAME_SEQ", allocationSize = 1)
    private long gameId;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinColumn(name = "developerId")
    private Developer developer;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.REMOVE,
            orphanRemoval = false,
            fetch=FetchType.EAGER
    )
    private List<Review> reviewList; // selfnote: getters and setters here break http methods

    public Game(String name, Developer developer) {
        this.name = name;
        this.developer = developer;
    }

    public Game() {
    }

    public OptionalDouble getAvgRating() {
        return reviewList.stream()
                .mapToDouble(review -> review.getRating())
                .average();
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
        return gameId == game.gameId &&
                name.equals(game.name) &&
                developer.equals(game.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name, developer);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + gameId +
                ", name='" + name + '\'' +
                ", developer=" + developer +
                '}';
    }
}



