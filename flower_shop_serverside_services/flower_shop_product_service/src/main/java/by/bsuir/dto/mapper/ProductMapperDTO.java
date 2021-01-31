package by.bsuir.dto.mapper;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.ProductDTO;
import by.bsuir.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperDTO extends AbstractMapperDTO<Product, ProductDTO> {

    @Autowired
    public ProductMapperDTO() {
        super(Product.class, ProductDTO.class);
    }
}