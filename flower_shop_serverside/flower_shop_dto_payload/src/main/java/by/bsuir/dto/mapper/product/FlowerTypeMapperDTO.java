package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.entity.product.bouqet.BouquetType;
import by.bsuir.entity.product.flower.FlowerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerTypeMapperDTO extends AbstractMapperDTO<FlowerType, FlowerTypeDTO> {

    @Autowired
    public FlowerTypeMapperDTO() {
        super(FlowerType.class, FlowerTypeDTO.class);
    }
}