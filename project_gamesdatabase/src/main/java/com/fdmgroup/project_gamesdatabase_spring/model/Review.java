package com.fdmgroup.project_gamesdatabase_spring.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_gen")
    @SequenceGenerator(name = "review_gen", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column
    private int rating;

    @Column
    private String review;

    public Review(User user, Game game, int rating, String review) {
        this.user = user;
        this.game = game;
        this.rating = rating;
        this.review = review;
    }

    public Review() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return id == review1.id && rating == review1.rating && user.equals(review1.user) && game.equals(review1.game) && Objects.equals(review, review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, game, rating, review);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", game=" + game +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
