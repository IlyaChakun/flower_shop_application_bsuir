package by.bsuir.dto.mapper.product;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.product.CategoryDTO;
import by.bsuir.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperDTO extends AbstractMapperDTO<Category, CategoryDTO> {

    @Autowired
    public CategoryMapperDTO() {
        super(Category.class, CategoryDTO.class);
    }
}