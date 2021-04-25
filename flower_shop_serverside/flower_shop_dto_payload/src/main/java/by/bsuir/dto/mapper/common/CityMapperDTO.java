package by.bsuir.dto.mapper.common;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.common.CityDTO;
import by.bsuir.entity.common.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapperDTO extends AbstractMapperDTO<City, CityDTO> {

    @Autowired
    public CityMapperDTO() {
        super(City.class, CityDTO.class);
    }
}