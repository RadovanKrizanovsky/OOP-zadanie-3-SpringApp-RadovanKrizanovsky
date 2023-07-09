package sk.stuba.fei.uim.oop.assignment3.book.database;

import javax.persistence.*;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long author;
    private Integer pages;
    private Integer amount;
    private Integer lendCount;

    public Book(BookRequest bookRequest) {
        this.name = bookRequest.getName();
        this.description = bookRequest.getDescription();
        this.author = bookRequest.getAuthor();
        this.pages = bookRequest.getPages();
        this.amount = bookRequest.getAmount();
        this.lendCount = bookRequest.getLendCount();
    }
}
