package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.entity.product.bouqet.FlowerBouquet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowerBouquetMapperDTO extends AbstractMapperDTO<FlowerBouquet, FlowerBouquetDTO> {

    @Autowired
    public FlowerBouquetMapperDTO() {
        super(FlowerBouquet.class, FlowerBouquetDTO.class);
    }
}