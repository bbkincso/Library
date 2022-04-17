package com.library.Borrowing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="borrowings")
@ApiModel(description = "Borrowing model of Borrowing Service")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "The id of Borrowing.")
    private Long borrowingId;

    @CreationTimestamp
    @Column(name="start_date", nullable = false, updatable=false)
    @ApiModelProperty(notes = "The date when the borrowing started.")
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "end_date")
    @ApiModelProperty(notes = "The date when the borrowing ends.")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The status of borrowing. (Open, Closed or Late")
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "The id of the user who borrowed a book.")
    private User user;

    @Column(name = "book_id")
    @ApiModelProperty(notes = "The id of the book which has been borrowed.")
    private Long bookId;

    @Transient
    @ApiModelProperty(notes = "The description of the book.")
    private Book bookInfo;

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

    public Book getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(Book bookInfo) {
        this.bookInfo = bookInfo;
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
