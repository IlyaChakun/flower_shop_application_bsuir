package by.bsuir.service;

import by.bsuir.dto.mapper.CategoryMapperDTO;
import by.bsuir.dto.model.CategoryDTO;

import by.bsuir.entity.Category;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapperDTO categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findDistinctByChildrenIsNotNull();
//        List<CategoryDTO> categoryDTOList = categoryMapper.toDtoList(categories);
//        List<CategoryListDTO> list = new ArrayList<>();
//
//        for (CategoryDTO category : categoryDTOList) {
//
//            CategoryListDTO categoryListDTO =  new CategoryListDTO();
//            categoryListDTO.setCategory(category);
//
//
//            categoryListDTO.setNestedCategories();
//        }

        return categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDTO getOne(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category with id=" + id + " not found!"));
        return categoryMapper.toDto(category);
    }
}
