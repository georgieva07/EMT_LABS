package mk.ukim.finki.emt_labs.service;

import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.exception.InvalidCountryIdException;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id) throws InvalidCountryIdException;

    Optional<Country>  create(String name, String continent);

    Optional<Country>  update(Long id, String name, String contintent) throws InvalidCountryIdException;

    List<Country> listCountries();

    Country delete(Long id) throws InvalidCountryIdException;

}