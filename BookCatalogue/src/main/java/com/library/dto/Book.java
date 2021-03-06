package com.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
@ApiModel(description = "Book model for Book Catalogue Service")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The id of a book.")
    private Long id;
    @NotEmpty
    @ApiModelProperty(notes = "The author/authors of a book. Need to be at lest 2 characters.")
    @Size(min = 2, message = "Author should be at least 2 characters.")
    private String author;
    @NotEmpty
    @ApiModelProperty(notes = "The title of a book. Need to be at lest 2 characters.")
    @Size(min = 2, message = "Title should be at least 2 characters.")
    private String title;
    @Pattern(regexp = "(ISBN[-]*(1[03])*[ ]*(: ){0,1})*(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})", message = "ISBN number is not correct.")
    @NotEmpty
    @ApiModelProperty(notes = "The ISBN number of a book. It must be in a valid ISBN-10 or ISBN-13 format.")
    private String isbn;
    @NotNull
    @Column(name = "is_available")
    @ApiModelProperty(notes = "Shows if a book is available to borrow.")
    private boolean isAvailable;

    public Book() {
    }

    public Book(String author, String title, String isbn, boolean isAvailable) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
