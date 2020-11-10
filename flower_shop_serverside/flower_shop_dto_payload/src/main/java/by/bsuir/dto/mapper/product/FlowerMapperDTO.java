package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.entity.product.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerMapperDTO extends AbstractMapperDTO<Flower, FlowerDTO> {

    @Autowired
    public FlowerMapperDTO() {
        super(Flower.class, FlowerDTO.class);
    }
}