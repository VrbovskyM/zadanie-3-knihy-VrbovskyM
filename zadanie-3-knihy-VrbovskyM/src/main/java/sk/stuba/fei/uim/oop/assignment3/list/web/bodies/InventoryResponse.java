package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.list.data.Inventory;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class InventoryResponse {
    private Long id;
    private List<BookResponse> lendingList;
    private boolean lended;

    public InventoryResponse(Inventory inv) {
        this.id = inv.getId();
        this.lended = inv.isLended();
        this.lendingList = new ArrayList<>();
        if (inv.getLendingList() != null) {
            for(Book book : inv.getLendingList()) {
                lendingList.add(new BookResponse(book));
            }
        }
    }
}

