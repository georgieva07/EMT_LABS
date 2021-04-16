package mk.ukim.finki.emt_labs.service.implementation;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.Category;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.model.exception.InvalidBookIdException;
import mk.ukim.finki.emt_labs.model.exception.NoAvailableBookCopiesException;
import mk.ukim.finki.emt_labs.repository.AuthorRepository;
import mk.ukim.finki.emt_labs.repository.BookRepository;
import mk.ukim.finki.emt_labs.service.AuthorService;
import mk.ukim.finki.emt_labs.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImplementation(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Optional<Book> findById(Long id) throws InvalidBookIdException {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));
    }

    @Override
    public Optional<Book> create(String name, Category category, List<Long> authorIds, Integer availableCopies) {
        List<Author> authors = this.authorService.findAllById(authorIds);
        Book book = new Book(name, category, authors, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, List<Long> authorIds, Integer availableCopies) throws InvalidBookIdException {
        List<Author> authors = this.authorService.findAllById(authorIds);
        Book book = this.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthors(authors);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Book delete(Long id) throws InvalidBookIdException {
        Book book = this.findById(id).orElseThrow(InvalidBookIdException::new);
        this.bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book  markAsTaken(Long id) throws InvalidBookIdException, NoAvailableBookCopiesException {
        Book book = this.findById(id).orElseThrow(InvalidBookIdException::new);
        if(book.getAvailableCopies()>0)
             book.setAvailableCopies(book.getAvailableCopies()-1);
        else
            throw new NoAvailableBookCopiesException();
        return book;
    }
}