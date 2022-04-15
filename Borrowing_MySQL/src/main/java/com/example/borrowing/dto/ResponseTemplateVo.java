package com.example.borrowing.dto;

public class ResponseTemplateVo {

    private Borrowing borrowing;
    private User user;
    private Book book;


    public ResponseTemplateVo() {
    }

    public ResponseTemplateVo(Borrowing borrowing, Book book) {
        this.borrowing = borrowing;
//        this.user = user;
        this.book = book;

    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "ResponseTemplateVo{" +
                "borrowing=" + borrowing +
//                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
