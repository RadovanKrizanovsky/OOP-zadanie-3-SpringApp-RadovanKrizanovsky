package sk.stuba.fei.uim.oop.assignment3.author.web;

import java.util.stream.Collectors;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.service.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.*;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping("/author")
@RestController
public class AuthorController {
    private final String JSON = MediaType.APPLICATION_JSON_VALUE;
    @Autowired
    private IAuthorService authorService;
    @DeleteMapping(value = "/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        this.authorService.delete(authorId);
    }
    @PutMapping(value = "/{id}",consumes=JSON,produces=JSON)
    public AuthorResponse updateAuthor(@PathVariable("id") Long authorId,@RequestBody AuthorUpdateRequest authorUpdateRequest) throws NotFoundException {
        return new AuthorResponse(this.authorService.update(authorId,authorUpdateRequest));
    }
    @GetMapping(value = "/{id}",produces=JSON)
    public AuthorResponse getAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        return new AuthorResponse(this.authorService.getById(authorId));
    }
    @PostMapping(consumes=JSON,produces=JSON)
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest authorRequest) {
        return new ResponseEntity<>(new AuthorResponse(this.authorService.create(authorRequest)),HttpStatus.CREATED);
    }
    @GetMapping(produces=JSON)
    public List<AuthorResponse> getAllAuthors() {
        return this.authorService.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }
}
