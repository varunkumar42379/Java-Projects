package org.example;

import java.util.List;

public interface LibraryDAO {
    boolean addBook(Book book);
    List<Book> getAllBooks();
    boolean addMember(Member member);
    Book getBookById(int bookId);
    Member getMemberById(int memberId);
    boolean updateBookQuantity(int bookId,int newAvailableQuantity);
    boolean deleteBook(int bookId);
    boolean deleteMember(int memberId);
}
