package by.bsuir.dto.mapper;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.CategoryDTO;
import by.bsuir.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperDTO extends AbstractMapperDTO<Category, CategoryDTO> {

    @Autowired
    public CategoryMapperDTO() {
        super(Category.class, CategoryDTO.class);
    }
}