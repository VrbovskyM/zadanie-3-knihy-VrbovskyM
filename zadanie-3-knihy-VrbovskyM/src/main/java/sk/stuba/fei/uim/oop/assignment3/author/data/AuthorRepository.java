package sk.stuba.fei.uim.oop.assignment3.author.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long id);
    List<Author> findAll();
}
