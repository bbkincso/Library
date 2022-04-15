package com.library.bookborrower.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Borrowing {

        private Long borrowingId;
        private Date startDate;
        private Date endDate;
        private Status status;
        private Object user;
        private Long bookId;
        private Object bookInfo;

        public Borrowing() {
        }

        public Borrowing(Long borrowingId, Date startDate, Date endDate, Status status, Object user, Long bookId, Object bookInfo) {
            this.borrowingId = borrowingId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.status = status;
            this.user = user;
            this.bookId = bookId;
            this.bookInfo = bookInfo;
        }

    public Long getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(Long borrowingId) {
        this.borrowingId = borrowingId;
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

    public Object getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(Object bookInfo) {
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
                    ", bookInfo=" + bookInfo +
                    '}';
        }
    }

