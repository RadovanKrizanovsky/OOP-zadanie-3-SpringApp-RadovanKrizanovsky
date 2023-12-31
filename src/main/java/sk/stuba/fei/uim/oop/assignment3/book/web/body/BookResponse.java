package sk.stuba.fei.uim.oop.assignment3.book.web.body;

import sk.stuba.fei.uim.oop.assignment3.book.database.Book;
import lombok.Getter;

@Getter
public class BookResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long author;
    private final Integer pages;
    private final Integer amount;
    private final Integer lendCount;
    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor() == null ? 0 : book.getAuthor();
        this.pages = book.getPages();
        this.amount = book.getAmount();
        this.lendCount = book.getLendCount();
    }
}
