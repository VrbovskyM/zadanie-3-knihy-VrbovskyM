package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();
    Author create(AuthorRequest request);
    Author getAuthorById(long id) throws NotFoundException;
    Author updateAuthorById(AuthorRequest request, long id) throws NotFoundException;
    void deleteAuthorById(long id) throws NotFoundException;


}
