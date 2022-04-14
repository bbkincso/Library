package com.example.borrowing.dto;


import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="borrowings")
@ApiModel(description = "Book model description")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @CreationTimestamp
    @Column(name="start_date", nullable = false, updatable=false)
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "borrowing_books",
            joinColumns = @JoinColumn(name = "borrowing_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Borrowing() {
    }

    public Borrowing(Date startDate, Date endDate, Status status, User user, List<Book> books) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
        this.books = books;
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


        public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", user=" + user +
                ", books=" + books +
                '}';
    }
}
