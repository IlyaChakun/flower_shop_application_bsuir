package by.bsuir.service;

import by.bsuir.dto.CountryDTO;
import by.bsuir.dto.mapper.CountryMapperDTO;
import by.bsuir.entity.Country;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonServiceImpl implements CommonService {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private final CountryRepository countryRepository;
    private final CountryMapperDTO countryMapper;

    @Override
    @Cacheable("countries")
    public List<CountryDTO> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    @Cacheable("country")
    public CountryDTO getOneCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Country with id={} not found!", id);
                            return new ResourceNotFoundException("Country with id=" + id + " not found!");
                        }
                );
        return countryMapper.toDto(country);
    }

    @Override
    public Boolean existById(Long id) {
        return countryRepository.existsById(id);
    }

}
