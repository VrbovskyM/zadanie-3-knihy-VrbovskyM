package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
@Getter
@Setter
public class BookResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final long author;
    private final int pages;
    private final int amount;
    private final int lendCount;


    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.pages = book.getPages();
        this.amount = book.getAmount();
        this.lendCount = book.getLendCount();
        this.author = book.getAuthor().getId();
    }
}
