package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Book> listOfBooks;

    public Author(AuthorRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.listOfBooks = new ArrayList<>();
    }
    public void addBookToAuthor(Book book) {
        this.listOfBooks.add(book);
    }
    public void removeBookFromAuthor(Book book) {
        listOfBooks.remove(book);
    }
}

