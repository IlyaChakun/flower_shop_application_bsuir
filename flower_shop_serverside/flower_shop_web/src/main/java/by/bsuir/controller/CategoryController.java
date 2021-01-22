package by.bsuir.controller;

import by.bsuir.dto.model.product.CategoryDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") @PositiveLong String id) {

        CategoryDTO categoryDTO = categoryService.getOne(Long.valueOf(id));

        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CategoryDTO> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

}
