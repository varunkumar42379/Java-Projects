package org.example;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private int totalQuantity;
    private int availableQuantity;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Book(int bookId, String title, String author, int totalQuantity, int availableQuantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
    }

    public Book(String title, String author, int totalQuantity) {
        this.title = title;
        this.author = author;
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
