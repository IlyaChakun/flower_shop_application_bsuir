package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.dto.model.product.ProductSearchCriteria;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    //@Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponse> saveProduct(@RequestBody @Valid ProductDTO product,
                                                   BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        ProductDTO addedProduct = productService.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/products/{id}")
                .buildAndExpand(addedProduct.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Product registered successfully!"));
    }

    // @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") @PositiveLong String id,
                                             @RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ProductDTO updatedProduct = productService.update(productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String id) {
        productService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") @PositiveLong String id) {

        ProductDTO flower = productService.findById(Long.valueOf(id));

        return ResponseEntity.ok(flower);
    }


    @GetMapping
    public ResponseEntity<?> findAllProducts(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            ProductSearchCriteria productSearchCriteria) {

        PageWrapper<ProductDTO> wrapper = productService.findAll(page - 1, size, productSearchCriteria);

        return ResponseEntity.ok(wrapper);
    }

}
