package by.bsuir.repository;


import by.bsuir.entity.City;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends AbstractRepository<City> {

    Optional<City> findByCityName(String cityName);

    City getByCityName(String cityName);
}

