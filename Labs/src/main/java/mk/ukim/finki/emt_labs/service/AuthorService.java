package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Author;
import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.exception.InvalidAuthorIdException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id) throws InvalidAuthorIdException;

    List<Author> findAllById(List<Long> ids);

    Optional<Author> create(String name, String surname, Country country);

    Optional<Author> update(Long id, String name, String surname, Country country) throws InvalidAuthorIdException;

    Author delete(Long id) throws InvalidAuthorIdException;

    List<Author> listAuthors();
}