package sk.stuba.fei.uim.oop.assignment3.book.database;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    Book findBookById(Long bookId);
    List<Book> findAll();
}
