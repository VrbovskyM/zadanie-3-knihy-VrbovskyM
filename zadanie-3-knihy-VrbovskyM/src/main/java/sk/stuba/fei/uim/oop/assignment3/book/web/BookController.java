package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return this.service.getAllBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") long id) throws NotFoundException {
        return new BookResponse(this.service.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest body) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.service.createBook(body)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable("id") long id, @RequestBody BookRequest body) throws NotFoundException {
        return new BookResponse(this.service.updateBookById(body, id));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id) throws NotFoundException {
        this.service.deleteBookById(id);
    }

    @GetMapping("/{id}/amount")
    public Amount getBookAmount(@PathVariable("id") long id) throws NotFoundException {
        return this.service.getBookAmountById(id);
    }

    @PostMapping("/{id}/amount")
    public Amount addBookAmount(@PathVariable("id") long id, @RequestBody Amount body) throws NotFoundException {
        return this.service.addBookAmountById(id, body.getAmount());
    }

    @GetMapping("/{id}/lendCount")
    public Amount getBookLendCount(@PathVariable("id") long id) throws NotFoundException {
        return this.service.getBookLendCountById(id);
    }
}
