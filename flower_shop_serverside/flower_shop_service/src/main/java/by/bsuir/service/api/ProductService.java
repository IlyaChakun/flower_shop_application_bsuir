package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.dto.model.product.ProductSearchCriteria;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface ProductService extends
        CustomCrudService<ProductDTO>,
        DeleteOperationService<ProductDTO> {

    PageWrapper<ProductDTO> findAll(int page, int size, ProductSearchCriteria searchParams);

}
