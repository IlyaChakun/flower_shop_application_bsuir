package by.bsuir.dto.mapper;

import by.bsuir.dto.RegionDTO;
import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMapperDTO extends AbstractMapperDTO<Region, RegionDTO> {

    @Autowired
    public RegionMapperDTO() {
        super(Region.class, RegionDTO.class);
    }
}