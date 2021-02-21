package by.bsuir.service;

import by.bsuir.dto.mapper.CategoryMapperDTO;
import by.bsuir.dto.model.CategoryDTO;
import by.bsuir.entity.Category;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapperDTO categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        log.info("In find all categories");
        List<Category> categories = categoryRepository.findDistinctByChildrenIsNotNull();
        return categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDTO getOne(Long id) {
        log.info("In find category by id {}", id);
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category with id=" + id + " not found!"));
        return categoryMapper.toDto(category);
    }
}
