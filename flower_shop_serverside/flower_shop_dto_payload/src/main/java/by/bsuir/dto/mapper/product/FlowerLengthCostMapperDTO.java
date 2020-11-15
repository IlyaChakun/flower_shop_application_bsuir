package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.common.FlowerLengthCostDTO;
import by.bsuir.entity.product.common.FlowerLengthCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerLengthCostMapperDTO extends AbstractMapperDTO<FlowerLengthCost, FlowerLengthCostDTO> {

    @Autowired
    public FlowerLengthCostMapperDTO() {
        super(FlowerLengthCost.class, FlowerLengthCostDTO.class);
    }
}