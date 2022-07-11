package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    private int pages;
    private int amount;
    private int lendCount;


    public Book(BookRequest body) {
        this.name = body.getName();
        this.description = body.getDescription();
        this.pages = body.getPages();
        this.amount = body.getAmount();
        this.lendCount = body.getLendCount();
        this.author = new Author();
    }
}
