package by.bsuir.dto.mapper;

import by.bsuir.dto.CountryDTO;
import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperDTO extends AbstractMapperDTO<Country, CountryDTO> {

    @Autowired
    public CountryMapperDTO() {
        super(Country.class, CountryDTO.class);
    }
}