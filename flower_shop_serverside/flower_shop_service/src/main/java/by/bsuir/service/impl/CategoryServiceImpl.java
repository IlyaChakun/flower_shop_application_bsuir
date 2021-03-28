package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.CategoryMapperDTO;
import by.bsuir.dto.model.product.CategoryDTO;
import by.bsuir.entity.product.Category;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.product.CategoryRepository;
import by.bsuir.service.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapperDTO categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findDistinctByChildrenIsNotNull();

        for (Category childNode :categories) {
            traverseNodeAndFetchChildren(childNode);
        }

        List<Category> finalCategories = new ArrayList<>();
        categories.forEach(category -> {
            if(Objects.isNull(category.getParentId())){
                finalCategories.add(category);
            }
        });

        return categoryMapper.toDtoList(finalCategories);
    }

    public void traverseNodeAndFetchChildren(Category node) {
        int size = node.getChildren().size();

        if (size > 0) {
            for (Category childNode : node.getChildren()) {
                List<Category> newChildren = childNode.getChildren().stream().distinct().collect(Collectors.toList());
                childNode.setChildren(newChildren);
                traverseNodeAndFetchChildren(childNode);
            }
        }


    }

    @Override
    public CategoryDTO getOne(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category with id=" + id + " not found!"));
        return categoryMapper.toDto(category);
    }
}
