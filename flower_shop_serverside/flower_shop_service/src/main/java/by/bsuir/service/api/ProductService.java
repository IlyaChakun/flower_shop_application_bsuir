package by.bsuir.service.api;


import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.FindAllPageableSortableWithSearchCriteriaOperationService;

public interface ProductService extends
        CustomCrudService<ProductDTO>,
        DeleteOperationService<ProductDTO>,
        FindAllPageableSortableWithSearchCriteriaOperationService<ProductDTO> {
}
