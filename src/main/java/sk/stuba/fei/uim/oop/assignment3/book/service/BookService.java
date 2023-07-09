package sk.stuba.fei.uim.oop.assignment3.book.service;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.database.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;

@Primary
@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void removeAmount(Long bookId,Integer subtract) throws NotFoundException,IllegalOperationException {
        Book book = this.getById(bookId);
        if (book.getAmount()<subtract) {
            throw new IllegalOperationException();
        }
        book.setAmount(book.getAmount()-subtract);
        this.bookRepository.save(book);
    }
    @Override
    public Integer getLendCount(Long bookId) throws NotFoundException {
        return this.getById(bookId).getLendCount();
    }
    @Override
    public Integer addAmount(Long bookId,Integer add) throws NotFoundException {
        Book book = this.getById(bookId);
        book.setAmount(book.getAmount()+add);
        this.bookRepository.save(book);
        return book.getAmount();
    }
    @Override
    public void incrementLendCount(long bookId) throws NotFoundException {
        Book book = this.getById(bookId);
        book.setAmount(book.getAmount() + 1);
        this.bookRepository.save(book);
    }

    public void decrementLendCount(long bookId) throws NotFoundException {
        Book book = this.getById(bookId);
        book.setAmount(book.getAmount() - 1);
        this.bookRepository.save(book);
    }

    @Override
    public Integer getAmount(Long bookId) throws NotFoundException {
        return this.getById(bookId).getAmount();
    }
    @Override
    public void delete(Long bookId) throws NotFoundException {
        this.bookRepository.delete(this.getById(bookId));
    }
    @Override
    public Book update(Long bookId,BookUpdateRequest bookUpdateRequest) throws NotFoundException {
        Book book = this.getById(bookId);
        if (bookUpdateRequest.getAuthor()!=null && bookUpdateRequest.getAuthor()!=0) {
            book.setAuthor(bookUpdateRequest.getAuthor());
        }
        if (bookUpdateRequest.getPages()!=null && bookUpdateRequest.getPages()!=0) {
            book.setPages(bookUpdateRequest.getPages());
        }
        if (bookUpdateRequest.getDescription()!=null) {
            book.setDescription(bookUpdateRequest.getDescription());
        }
        if (bookUpdateRequest.getName()!=null) {
            book.setName(bookUpdateRequest.getName());
        }
        return this.bookRepository.save(book);
    }
    @Override
    public Book getById(Long bookId) throws NotFoundException {
        Book book = this.findBookByID(bookId);
        if (book==null) {
            throw new NotFoundException();
        }
        return book;
    }
    @Override
    public Book create(BookRequest bookRequest) {
        return this.bookRepository.save(new Book(bookRequest));
    }
    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }
    private Book findBookByID(Long idSearch) {
        return this.bookRepository.findBookById(idSearch);
    }
}
