package mk.ukim.finki.emt_labs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToMany
    private List<Author> authors;

    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, Category category, List<Author> authors, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authors = authors;
        this.availableCopies = availableCopies;
    }
}