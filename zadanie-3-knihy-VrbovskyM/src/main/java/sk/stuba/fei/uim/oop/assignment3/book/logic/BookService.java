package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
@Lazy
@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IAuthorService authorService;
    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book createBook(BookRequest request) throws NotFoundException {
        Book book = this.bookRepository.save(new Book(request));
        Author author = this.authorService.getAuthorById(request.getAuthor());
        book.setAuthor(author);
        author.addBookToAuthor(book);
        return book;
    }

    @Override
    public Book getBookById(long id) throws NotFoundException {
        Book book = this.bookRepository.findBookById(id);
        if (book == null) throw new NotFoundException();
        return book;
    }

    @Override
    public Book updateBookById(BookRequest request, long id) throws NotFoundException {
        Book book = this.getBookById(id);
        if (request.getName() != null) {
            book.setName(request.getName());
            this.bookRepository.save(book);
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
            this.bookRepository.save(book);
        }
        if (request.getAuthor() != 0) {
            book.setAuthor(authorService.getAuthorById(request.getAuthor()));
            this.bookRepository.save(book);
        }
        if (request.getPages() != 0) {
            book.setPages(request.getPages());
            this.bookRepository.save(book);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBookById(long bookId) throws NotFoundException {
        Book book = getBookById(bookId);
       for(Author a : this.authorService.getAll()) {
            for(Book b : a.getListOfBooks()) {
                if (b.getId().equals(bookId)) {
                    a.removeBookFromAuthor(b);
                    break;
                }
                break;
            }
           break;
        }
        this.bookRepository.delete(book);
    }

    @Override
    public Amount getBookAmountById(long id) throws NotFoundException {
        Book book = getBookById(id);
        return new Amount(book.getAmount());
    }

    @Override
    public Amount addBookAmountById(long id, int amount) throws NotFoundException {
        Book book = getBookById(id);
        book.setAmount(book.getAmount() + amount);
        this.bookRepository.save(book);
        return new Amount(book.getAmount());
    }

    @Override
    public Amount getBookLendCountById(long id) throws NotFoundException {
        Book book = getBookById(id);
        return new Amount(book.getLendCount());
    }

    @Override
    public void updateLendCount(Book book, int count) {
        book.setLendCount(book.getLendCount() + count);
        this.bookRepository.save(book);
    }
}
