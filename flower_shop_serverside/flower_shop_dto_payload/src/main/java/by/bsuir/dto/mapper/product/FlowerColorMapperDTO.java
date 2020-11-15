package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.entity.product.common.FlowerColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerColorMapperDTO extends AbstractMapperDTO<FlowerColor, FlowerColorDTO> {

    @Autowired
    public FlowerColorMapperDTO() {
        super(FlowerColor.class, FlowerColorDTO.class);
    }
}