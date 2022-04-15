package com.library.bookborrower.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Borrowing {

        private long id;
        private Date startDate;
        private Date endDate;
        private Status status;
        private Object user;
        private Long bookId;

        public Borrowing() {
        }

        public Borrowing(Date startDate, Date endDate, Status status, Object user, Long bookId) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = status;
            this.user = user;
            this.bookId = bookId;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
        public String toString() {
            return "Borrowing{" +
                    "id=" + id +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", status=" + status +
                    ", user=" + user +
                    ", bookId=" + bookId +
                    '}';
        }
    }

