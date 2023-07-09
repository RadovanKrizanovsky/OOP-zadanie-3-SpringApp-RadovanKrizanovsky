package sk.stuba.fei.uim.oop.assignment3.book.web;

import java.util.stream.Collectors;
import java.util.List;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.book.service.IBookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping("/book")
@RestController
public class BookController {
    private final String JSON = MediaType.APPLICATION_JSON_VALUE;
    @Autowired
    private IBookService bookService;

    @PostMapping(value = "/{id}/amount",consumes=JSON,produces=JSON)
    public Amount addAmount(@PathVariable("id") Long bookId,@RequestBody Amount amountBody) throws NotFoundException {
        return new Amount(this.bookService.addAmount(bookId,amountBody.getAmount()));
    }
    @GetMapping(value = "/{id}/amount",produces=JSON)
    public Amount getAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.bookService.getAmount(bookId));
    }

    @GetMapping(value = "/{id}/lendCount",produces=JSON)
    public Amount getLendCount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.bookService.getLendCount(bookId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFoundException {
        this.bookService.delete(bookId);

    }
    @PutMapping(value = "/{id}",consumes=JSON,produces=JSON)
    public BookResponse updateBook(@PathVariable("id") Long bookId,@RequestBody BookUpdateRequest bookUpdateRequestBody) throws NotFoundException {
        return new BookResponse(this.bookService.update(bookId,bookUpdateRequestBody));
    }
    @GetMapping(value = "/{id}",produces=JSON)
    public BookResponse getBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return new BookResponse(this.bookService.getById(bookId));
    }
    @PostMapping(consumes=JSON,produces=JSON)
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequestBody) {
        return new ResponseEntity<>(new BookResponse(this.bookService.create(bookRequestBody)),HttpStatus.CREATED);
    }
    @GetMapping(produces=JSON)
    public List<BookResponse> getAllBooks() {
        return this.bookService.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }
}
