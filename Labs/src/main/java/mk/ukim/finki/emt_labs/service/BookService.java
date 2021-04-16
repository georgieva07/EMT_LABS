package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Book;
import mk.ukim.finki.emt_labs.model.Category;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt_labs.model.exception.InvalidBookIdException;
import mk.ukim.finki.emt_labs.model.exception.NoAvailableBookCopiesException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id) throws InvalidBookIdException;

    Optional<Book> create(String name, Category category, List<Long> authorIds, Integer availableCopies) throws InvalidAuthorIdException;

    Optional<Book> update(Long id, String name, Category category, List<Long> authorIds, Integer availableCopies) throws InvalidBookIdException, InvalidAuthorIdException;

    Book delete(Long id) throws InvalidBookIdException;

    List<Book> listBooks();

    Book  markAsTaken(Long id) throws InvalidBookIdException, NoAvailableBookCopiesException;
}