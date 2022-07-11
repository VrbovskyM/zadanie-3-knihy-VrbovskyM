package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.Inventory;

import java.util.List;

public interface IInventoryService {
    List<Inventory> getAllInventories();
    Inventory createInventory();
    Inventory getInventoryById(long id) throws NotFoundException;
    void deleteInventory(long id) throws NotFoundException;
    Inventory addBookToInventory(long inventoryId, long bookId) throws NotFoundException, IllegalOperationException;
    void removeBookFromInventory(long inventoryId, long bookId) throws NotFoundException, IllegalOperationException;
    void lendInventory(Long id) throws NotFoundException, IllegalOperationException;
}
