package by.bsuir.dto.mapper.common;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.common.address.RegionDTO;
import by.bsuir.entity.common.address.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMapperDTO extends AbstractMapperDTO<Region, RegionDTO> {

    @Autowired
    public RegionMapperDTO() {
        super(Region.class, RegionDTO.class);
    }
}