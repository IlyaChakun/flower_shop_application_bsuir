package by.bsuir.dto.mapper.common;


import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.entity.common.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperDTO extends AbstractMapperDTO<Country, CountryDTO> {

    @Autowired
    public CountryMapperDTO() {
        super(Country.class, CountryDTO.class);
    }
}