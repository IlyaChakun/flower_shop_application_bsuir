package by.bsuir.service;


import by.bsuir.dto.model.ProductDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.FindAllPageableSearchableService;


public interface ProductService extends
        CustomCrudService<ProductDTO>,
        DeleteOperationService<ProductDTO>,
        FindAllPageableSearchableService<ProductDTO> {
}
