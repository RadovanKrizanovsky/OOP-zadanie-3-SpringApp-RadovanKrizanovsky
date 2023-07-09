package sk.stuba.fei.uim.oop.assignment3.book.web.body;

import lombok.Data;

@Data
public class BookRequest {
    private String name;
    private String description;
    private Long author;
    private Integer pages;
    private Integer amount;
    private Integer lendCount;
}
