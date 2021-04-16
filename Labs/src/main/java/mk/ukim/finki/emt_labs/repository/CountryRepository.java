package mk.ukim.finki.emt_labs.repository;

import mk.ukim.finki.emt_labs.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
