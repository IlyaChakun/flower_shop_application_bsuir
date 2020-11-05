package by.bsuir.service.api;


import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.FindAllOperationService;

public interface ShopService extends
        CustomCrudService<ShopDTO>,
        DeleteOperationService<ShopDTO>,
        FindAllOperationService<ShopDTO> {

}
