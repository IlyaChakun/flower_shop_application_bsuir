package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import by.bsuir.entity.product.common.FlowerSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerSortMapperDTO extends AbstractMapperDTO<FlowerSort, FlowerSortDTO> {

    @Autowired
    public FlowerSortMapperDTO() {
        super(FlowerSort.class, FlowerSortDTO.class);
    }
}