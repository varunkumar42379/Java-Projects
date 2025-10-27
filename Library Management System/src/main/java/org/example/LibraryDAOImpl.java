package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAOImpl implements LibraryDAO{
    static Connection con=DatabaseConnection.getConnection();

    @Override
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (title, author, total_quantity, available_quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getTotalQuantity());
            ps.setInt(4, book.getAvailableQuantity());

            int rowsInserted = ps.executeUpdate();

            // If one or more rows were inserted successfully
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> books=new ArrayList<>();

        String sql="SELECT * FROM book WHERE available_quantity > 0";

        try(Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql)){

            while(rs.next()){
                Book b=new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_quantity"),
                        rs.getInt("available_quantity")
                );
                books.add(b);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean addMember(Member member) {
        String sql="INSERT INTO member(member_id, name, contact_info) VALUES(?,?,?)";

        try(PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,member.getMemberId());
            ps.setString(2,member.getName());
            ps.setString(3, member.getContactInfo());
            int rowInserted=ps.executeUpdate();

            return rowInserted>0;
        } catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }

    @Override
    public Book getBookById(int bookId) {
        // Note: It's safer to get a new connection and close resources in each method.
        // Assuming 'con' is the static connection from your provided code for continuity.
        String sql = "SELECT book_id, title, author, total_quantity, available_quantity FROM book WHERE book_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("total_quantity"),
                            rs.getInt("available_quantity")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching book by ID " + bookId + ": " + e.getMessage());
        }
        return null; // Return null if not found or on error
    }

    @Override
    public Member getMemberById(int memberId) {
        String sql = "SELECT member_id, name, contact_info FROM member WHERE member_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getInt("member_id"),
                            rs.getString("name"),
                            rs.getString("contact_info")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching member by ID " + memberId + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateBookQuantity(int bookId, int newAvailableQuantity) {
        String sql = "UPDATE book SET available_quantity = ? WHERE book_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newAvailableQuantity);
            ps.setInt(2, bookId);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected == 1; // Success if exactly one row was updated

        } catch (SQLException e) {
            System.err.println("Error updating book quantity for ID " + bookId + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting book with ID " + bookId + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM member WHERE member_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting member with ID " + memberId + ": " + e.getMessage());
        }
        return false;
    }


}
