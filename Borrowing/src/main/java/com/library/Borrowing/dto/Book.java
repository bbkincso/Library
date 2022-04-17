package com.library.Borrowing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Book model for Borrowing Service")
public class Book {

    @ApiModelProperty(notes = "The id of a book.")
    private Long id;
    @ApiModelProperty(notes = "The author/authors of a book. Need to be at lest 2 characters.")
    private String author;
    @ApiModelProperty(notes = "The title of a book. Need to be at lest 2 characters.")
    private String title;
    @ApiModelProperty(notes = "The ISBN number of a book. It must be in a valid ISBN-10 or ISBN-13 format.")
    private String isbn;
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
