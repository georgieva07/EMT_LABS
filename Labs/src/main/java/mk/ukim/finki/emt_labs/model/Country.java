package mk.ukim.finki.emt_labs.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    public Country(String name, Category category, List<Author> authors, Integer availableCopies) {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
