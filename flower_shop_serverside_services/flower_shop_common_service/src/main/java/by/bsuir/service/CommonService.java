package by.bsuir.service;

import by.bsuir.dto.CountryDTO;

import java.util.List;

public interface CommonService {

    List<CountryDTO> findAllCountries();

    CountryDTO getOneCountry(Long id);

    Boolean existById(Long id);

}
