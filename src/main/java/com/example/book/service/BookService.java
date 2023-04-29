package com.example.book.service;

import com.example.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBook();
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBookByIsbn(String isbn);
}
