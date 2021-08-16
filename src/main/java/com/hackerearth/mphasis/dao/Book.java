package com.hackerearth.mphasis.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Books")
public class Book implements Serializable {

    @Id
    @JsonProperty("bookID")
    private long bookID;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("title")
    private String title;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("authors")
    private String authors;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("average_rating")
    private String averageRating;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("isbn")
    private String isbn;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("language_code")
    private String languageCode;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("ratings_count")
    private String ratingCount;

    @JsonProperty("price")
    private int price;

    public Book(long bookID, String title, String authors, String averageRating, String isbn, String languageCode, String ratingCount, int price) {
        this.bookID = bookID;
        this.title = title;
        this.authors = authors;
        this.averageRating = averageRating;
        this.isbn = isbn;
        this.languageCode = languageCode;
        this.ratingCount = ratingCount;
        this.price = price;
    }

    public Book() {
    }

    public long getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", averageRating=" + averageRating +
                ", isbn='" + isbn + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", ratingCount=" + ratingCount +
                ", price=" + price +
                '}';
    }
}