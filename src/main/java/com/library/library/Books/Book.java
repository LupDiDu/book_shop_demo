package com.library.library.Books;
import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String name;
    private Long authorId;
    private String yearOfPublishing;
    private String description;
    private String image;
    private int cost;
    private int score;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    private ArrayList<String> genres = new ArrayList<String>();

    public Book(String name, Long authorId, String yearOfPublishing,
                int cost, int score, ArrayList<String> genres) {
        this.name = name;
        this.authorId = authorId;
        this.yearOfPublishing = yearOfPublishing;
        this.cost = cost;
        this.score = score;
        this.genres = genres;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public Book(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthor(Long authorId) {
        this.authorId = authorId;
    }

    public String getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(String yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", yearOfPublishing='" + yearOfPublishing + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", cost=" + cost +
                ", score=" + score +
                ", genres=" + genres +
                '}';
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
