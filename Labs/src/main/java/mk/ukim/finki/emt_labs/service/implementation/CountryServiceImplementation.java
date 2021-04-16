package mk.ukim.finki.emt_labs.service.implementation;

import mk.ukim.finki.emt_labs.model.Country;
import mk.ukim.finki.emt_labs.model.exception.InvalidCountryIdException;
import mk.ukim.finki.emt_labs.repository.CountryRepository;
import mk.ukim.finki.emt_labs.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) throws InvalidCountryIdException {
        return Optional.of(this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country coutry = new Country(name, continent);
        return Optional.of(this.countryRepository.save(coutry));
    }

    @Override
    public Optional<Country> update(Long id, String name, String contintent) throws InvalidCountryIdException {
        Country country = this.findById(id).orElseThrow(InvalidCountryIdException::new);
        country.setName(name);
        country.setContinent(contintent);
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public List<Country> listCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country delete(Long id) throws InvalidCountryIdException {
        Country country = this.findById(id).orElseThrow(InvalidCountryIdException::new);
        this.countryRepository.delete(country);
        return country;
    }
}
