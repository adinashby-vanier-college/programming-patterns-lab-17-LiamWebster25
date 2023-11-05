/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab17;

/**
 *
 * @author webst
 */
public class BookController {
    private BookList bookList;
    
    public BookController(BookList bookList) {
        this.bookList = bookList;
    }
    
    public void addBook(String title, String author) {
        Book b = new Book(title, author);
        bookList.addBook(b);
    }
}

