package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.Inventory;
import sk.stuba.fei.uim.oop.assignment3.list.data.InventoryRepository;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {
    @Autowired
    private InventoryRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<Inventory> getAllInventories() {
        return this.repository.findAll();
    }

    @Override
    public Inventory createInventory() {
        return this.repository.save(new Inventory());
    }

    @Override
    public Inventory getInventoryById(long id) throws NotFoundException {
        Inventory inv = this.repository.findInventoryById(id);
        if (inv == null) {
            throw new NotFoundException();
        }
        return inv;
    }

    @Override
    public void deleteInventory(long id) throws NotFoundException{
        Inventory inv = getInventoryById(id);
        if (inv.getLendingList() != null) {
            for(Book book : inv.getLendingList()) {
                this.bookService.updateLendCount(book, -1);
            }
        }
        this.repository.delete(inv);
    }

    @Override
    public Inventory addBookToInventory(long inventoryId, long bookId) throws NotFoundException, IllegalOperationException {
        Inventory inv = getInventoryById(inventoryId);
        Book b = this.bookService.getBookById(bookId);
        if (inv.getLendingList() != null) {
            for(Book book : inv.getLendingList()) {
                if (book.getId() == bookId) {
                    throw new IllegalOperationException();
                }
            }
        }
        if (!inv.isLended() && b.getAmount() > b.getLendCount()) {
            inv.addBookToLendingList(b);
            this.repository.save(inv);
            return inv;
        }
        else throw new IllegalOperationException();
    }

    @Override
    public void removeBookFromInventory(long inventoryId, long bookId) throws NotFoundException {
        Inventory inv = getInventoryById(inventoryId);
        for(Book book : inv.getLendingList()) {
            if (book.getId() == bookId && !inv.isLended()) {
                inv.removeBookFromLendingList(book);
                this.repository.save(inv);
                return;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public void lendInventory(Long id) throws NotFoundException, IllegalOperationException {
        Inventory inv = getInventoryById(id);
        if (!inv.isLended()) {
            if (inv.getLendingList() != null) {
                for(Book book : inv.getLendingList()) {
                    this.bookService.updateLendCount(book, +1);
                }
            }
            inv.setLended(true);
            this.repository.save(inv);
        }
        else throw new IllegalOperationException();
    }
}
