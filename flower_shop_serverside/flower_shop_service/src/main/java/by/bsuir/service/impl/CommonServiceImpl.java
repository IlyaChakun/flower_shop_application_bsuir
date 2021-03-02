package by.bsuir.service.impl;

import by.bsuir.dto.mapper.common.CountryMapperDTO;
import by.bsuir.dto.mapper.common.ProductLengthMapperDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.common.ProductLength;
import by.bsuir.repository.api.common.CountryRepository;
import by.bsuir.repository.api.common.ProductLengthRepository;
import by.bsuir.service.api.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final ProductLengthRepository productLengthRepository;
    private final ProductLengthMapperDTO productLengthMapper;

    private final CountryRepository countryRepository;
    private final CountryMapperDTO countryMapper;

    @Override
    @Cacheable("productLengths")
    public List<ProductLengthDTO> findAllProductLengths() {
        List<ProductLength> productLengths = productLengthRepository.findAll();
        return productLengthMapper.toDtoList(productLengths);
    }


    @Override
    @Cacheable("countries")
    public List<CountryDTO> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }
}
