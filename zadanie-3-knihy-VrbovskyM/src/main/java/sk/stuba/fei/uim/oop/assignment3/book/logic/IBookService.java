package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book createBook(BookRequest request) throws NotFoundException;
    Book getBookById(long id) throws NotFoundException;
    Book updateBookById(BookRequest request, long id) throws NotFoundException;
    void deleteBookById(long id) throws NotFoundException;
    Amount getBookAmountById(long id) throws NotFoundException;
    Amount addBookAmountById(long id, int amount) throws NotFoundException;
    Amount getBookLendCountById(long id) throws NotFoundException;
    void updateLendCount(Book book, int count);
}
