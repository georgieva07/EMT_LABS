package mk.ukim.finki.emt_labs.service.implementation;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.repository.AuthorRepository;
import mk.ukim.finki.emt_labs.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) throws InvalidAuthorIdException {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new));
    }

    @Override
    public Optional<Author> create(String name, String surname, Country country) {
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Country country) throws InvalidAuthorIdException {
        Author author = this.findById(id).orElseThrow(InvalidAuthorIdException::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> findAllById(List<Long> ids) {
        return this.authorRepository.findAllById(ids);
    }

    @Override
    public Author delete(Long id) throws InvalidAuthorIdException {
        Author author = this.findById(id).orElseThrow(InvalidAuthorIdException::new);
        this.authorRepository.delete(author);
        return author;
    }
}