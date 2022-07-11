package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<Book> lendingList;
    private boolean lended;

    public Inventory() {
        this.lendingList = new ArrayList<>();
        this.lended = false;
    }
     public void addBookToLendingList(Book book) {
         this.lendingList.add(book);
     }
     public void removeBookFromLendingList(Book book) {
         this.lendingList.remove(book);
     }
}
