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
@ApiModel(description = "Borrowing model description")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long borrowingId;

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

    @Column(name = "book_id")
    private Long bookId;

    public Borrowing() {
    }

    public Borrowing(Date startDate, Date endDate, Status status, User user, Long bookId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(Long borrowingId) {
        this.borrowingId = borrowingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "id=" + borrowingId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", user=" + user +
                ", bookId=" + bookId +
                '}';
    }
}
