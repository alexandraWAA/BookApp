package com.example.book.dao;

import com.example.book.entity.Book;
import com.example.book.service.BookRowService;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO {
    private JdbcTemplate template;

    public BookDAOImpl(@Lazy JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void addBook(Book book) {
        template.update("INSERT INTO book VALUES (?, ?, ?, ?)",
                book.getBookName(), book.getBookAuthor(), book.getBookYear(), book.getIsbn());
    }
    @Override
    public void updateBook(Book book) {
        template.update("UPDATE book SET book_name = ?, book_author = ?, book_year = ? WHERE isbn = ?",
book.getBookName(), book.getBookAuthor(), book.getBookYear(), book.getIsbn());
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        template.update("DELETE FROM book WHERE isbn = ?", isbn);
    }
    @Override
    public Book getBookByIsbn(String isbn) {
        return template.query("SELECT * FROM book WHERE isbn = ?",
                new Object[] {isbn},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
@Override
    public List<Book> getBook() {
        return template.query("SELECT * FROM book", new BookRowService());
    }



}
