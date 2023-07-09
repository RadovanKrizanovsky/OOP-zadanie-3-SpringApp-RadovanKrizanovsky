package sk.stuba.fei.uim.oop.assignment3.library.web;

import java.util.stream.Collectors;
import java.util.List;
import sk.stuba.fei.uim.oop.assignment3.book.service.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.*;
import sk.stuba.fei.uim.oop.assignment3.library.web.body.*;
import sk.stuba.fei.uim.oop.assignment3.library.service.IListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping("/list")
@RestController
public class ListController {
    private final String JSON = MediaType.APPLICATION_JSON_VALUE;
    private final String TEXT = MediaType.TEXT_PLAIN_VALUE;
    @Autowired
    private IListService listService;
    @Autowired
    private IBookService bookService;

    @GetMapping(value = "/{id}",produces=JSON)
    public ListResponse infoBookList(@PathVariable("id") Long bookListId) throws NotFoundException {
        return new ListResponse(this.listService.getById(bookListId));
    }
    @PostMapping(value = "/{id}/add", consumes=JSON,produces=JSON)
    public ResponseEntity addBookToList(@PathVariable("id") Long listId, @RequestBody BookIdRequest bookIdRequest) throws NotFoundException, IllegalOperationException {
        this.bookService.incrementLendCount(bookIdRequest.getId());
        this.bookService.removeAmount(bookIdRequest.getId(), 1);
        return this.listService.addToList(listId, bookIdRequest.getId());
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long listId) throws NotFoundException {
        this.listService.delete(listId);
    }
    @GetMapping(produces=JSON)
    public List<ListResponse> getAllLists() {
        return this.listService.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }
    @PostMapping(produces=JSON)
    public ResponseEntity<ListResponse> addList() {
        return new ResponseEntity<>(new ListResponse(this.listService.create()),HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}/lend",produces=TEXT)
    public void makeBookListLended(@PathVariable("id") Long bookListId) throws NotFoundException,IllegalOperationException {
        this.listService.makeBookListLended(bookListId);
    }
}
