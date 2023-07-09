package sk.stuba.fei.uim.oop.assignment3.book.service;

import java.util.List;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.database.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.*;

public interface IBookService {
    void removeAmount(Long bookId,Integer subtract) throws NotFoundException,IllegalOperationException;
    Integer getLendCount(Long bookId) throws NotFoundException;
    Integer addAmount(Long bookId,Integer add) throws NotFoundException;
    Integer getAmount(Long bookId) throws NotFoundException;
    void delete(Long bookId) throws NotFoundException;
    Book update(Long bookId,BookUpdateRequest bookUpdateRequest) throws NotFoundException;
    Book getById(Long bookId) throws NotFoundException;
    Book create(BookRequest bookRequest);
    List<Book> getAll();
    void incrementLendCount(long bookId) throws NotFoundException;

    void decrementLendCount(long bookId) throws NotFoundException;
}
