package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.entity.product.bouqet.BouquetType;
import by.bsuir.entity.product.bouqet.FlowerBouquet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BouquetTypeMapperDTO extends AbstractMapperDTO<BouquetType, BouquetTypeDTO> {

    @Autowired
    public BouquetTypeMapperDTO() {
        super(BouquetType.class, BouquetTypeDTO.class);
    }
}