package com.example.book.dao;

import com.example.book.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBookByIsbn(String isbn);
    Book getBookByIsbn(String isbn);
    List<Book> getBook();

}
