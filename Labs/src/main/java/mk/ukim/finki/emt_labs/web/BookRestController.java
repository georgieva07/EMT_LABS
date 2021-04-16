package mk.ukim.finki.emt_labs.web;

import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.Category;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.model.exception.InvalidBookIdException;
import mk.ukim.finki.emt_labs.model.exception.NoAvailableBookCopiesException;
import mk.ukim.finki.emt_labs.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.listBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) throws InvalidBookIdException {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> create(@RequestParam String name, @RequestParam Category category, @RequestParam List<Long> authorIds, @RequestParam Integer availableCopies) throws InvalidAuthorIdException {
        return this.bookService.create(name, category, authorIds, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestParam String name, @RequestParam Category category, @RequestParam List<Long> authorIds, @RequestParam Integer availableCopies) throws InvalidAuthorIdException, InvalidBookIdException {
        return this.bookService.update(id, name, category, authorIds, availableCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws InvalidBookIdException {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/markAsTaken")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) throws InvalidBookIdException, NoAvailableBookCopiesException {
        Book book = this.bookService.markAsTaken(id);
        if(book!=null) return ResponseEntity.ok().body(book);
        return ResponseEntity.badRequest().build();
    }
}
