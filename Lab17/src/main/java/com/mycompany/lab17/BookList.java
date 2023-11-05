/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab17;

/**
 *
 * @author webst
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class BookList extends Observable {
    private List<Book> books;
    private static BookList list;
    private static final String database = "jdbc:sqlite:database.db";

    private BookList() {
        this.books = new ArrayList<>();
        DatabaseInitializer.initializeDatabase();
    }

    public static BookList getInstance() {
        if (list == null) {
            list = new BookList();
        }
        return list;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(database);
    }

    public void addBook(Book book) {

        String sql = "INSERT INTO books(title, author) VALUES(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        setChanged();
        notifyObservers(book);
    }

    public List<Book> getBooks() {
        String sql = "SELECT id, title, author FROM books";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            books.clear();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("ID"),
                        rs.getString("TITLE"),
                        rs.getString("AUTHOR"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}

