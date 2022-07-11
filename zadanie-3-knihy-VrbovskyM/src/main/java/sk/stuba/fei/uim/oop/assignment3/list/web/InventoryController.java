package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.IInventoryService;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.InventoryRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.InventoryResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class InventoryController {
    @Autowired
    private IInventoryService service;

    @GetMapping
    public List<InventoryResponse> getAllInventories() {
        return this.service.getAllInventories().stream().map(InventoryResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventory(@PathVariable("id") long id) throws NotFoundException {
        return new InventoryResponse(this.service.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory() {
        return new ResponseEntity<>(new InventoryResponse(this.service.createInventory()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable("id") long id) throws NotFoundException {
        this.service.deleteInventory(id);
    }

    @PostMapping("/{id}/add")
    public InventoryResponse addBookToInventory(@PathVariable("id") long inventoryId, @RequestBody InventoryRequest body) throws NotFoundException, IllegalOperationException {
        return new InventoryResponse(this.service.addBookToInventory(inventoryId, body.getId()));
    }

    @DeleteMapping("/{id}/remove")
    public void removeBookFromInventory(@PathVariable("id") long inventoryId, @RequestBody InventoryRequest body) throws NotFoundException, IllegalOperationException {
        this.service.removeBookFromInventory(inventoryId, body.getId());
    }

    @GetMapping("/{id}/lend")
    public void lendInventory(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        this.service.lendInventory(id);
    }
}
