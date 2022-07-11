package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class AuthorResponse {
    private final long id;
    private final String name;
    private final String surname;
    private ArrayList<Long> books;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        addBookIdToAuthorResponse(author.getListOfBooks());
    }
    private void addBookIdToAuthorResponse(List<Book> listOfBooks) {
        this.books = new ArrayList<>();
        for (Book book: listOfBooks) {
            books.add(book.getId());
        }
    }
}
