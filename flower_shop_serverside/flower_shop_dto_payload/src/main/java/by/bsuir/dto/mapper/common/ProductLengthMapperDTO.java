package by.bsuir.dto.mapper.common;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.entity.common.ProductLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductLengthMapperDTO extends AbstractMapperDTO<ProductLength, ProductLengthDTO> {

    @Autowired
    public ProductLengthMapperDTO() {
        super(ProductLength.class, ProductLengthDTO.class);
    }
}