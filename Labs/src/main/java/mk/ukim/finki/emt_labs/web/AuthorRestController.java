package mk.ukim.finki.emt_labs.web;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.listAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) throws InvalidAuthorIdException {
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> create(@RequestParam String name, @RequestParam String surname , @RequestParam Country country) {
        return this.authorService.create(name, surname, country)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestParam String name, @RequestParam String surname , @RequestParam Country country) throws InvalidAuthorIdException {
        return this.authorService.update(id, name, surname, country)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws InvalidAuthorIdException {
        this.authorService.delete(id);
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


}
