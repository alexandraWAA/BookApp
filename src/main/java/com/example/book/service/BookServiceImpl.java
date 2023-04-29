package com.example.book.service;

import com.example.book.dao.BookDAO;
import com.example.book.entity.Book;
import com.example.book.exceptions.BookException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getBook() {
        return bookDAO.getBook();
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        validate(book);
        bookDAO.addBook(book);
    }
    @Override
    @Transactional
    public void updateBook(Book book) {
        Book updateBook = this.bookDAO.getBookByIsbn(book.getIsbn());
        if (updateBook == null) {
            throw new NullPointerException();
        }
        updateBook.setBookName(book.getBookName());
        updateBook.setBookAuthor(book.getBookAuthor());
        updateBook.setBookYear(book.getBookYear());
        validate(updateBook);
        bookDAO.updateBook(book);

    }

    @Override
    @Transactional
    public void deleteBookByIsbn(String isbn) {
        Book book = bookDAO.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookException("Book with isbn=" + book.getIsbn() + "doesn't exist");
        }
        bookDAO.deleteBookByIsbn(isbn);
    }
    private  boolean validate (Book book) {
        if (book.getBookName() == null || book.getBookAuthor() == null || book.getBookYear() ==null ||
                book.getIsbn() == null || book.getBookYear() < 0) {
            throw new NullPointerException();
        }
        String curIsbn = book.getIsbn();
        String cleanIsbn = curIsbn.replaceAll("[\\-\\s]", "");
        if (cleanIsbn.length() != 13 && !cleanIsbn.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
        int a = 0;
        for (int i = 0; i < cleanIsbn.length(); i++) {
            int b = Character.getNumericValue(cleanIsbn.charAt(i));
            a = a + ((i % 2 == 0) ? b : b * 3);
        }
        int c = 10 - (a%10);
        return c == Character.getNumericValue(cleanIsbn.charAt(cleanIsbn.length()-1));
    }
}
