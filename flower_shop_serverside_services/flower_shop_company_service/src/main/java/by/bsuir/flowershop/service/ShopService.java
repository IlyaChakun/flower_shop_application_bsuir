package by.bsuir.flowershop.service;


import by.bsuir.flowershop.dto.model.shop.ShopDTO;
import by.bsuir.flowershop.service.core.CustomCrudService;
import by.bsuir.flowershop.service.core.base.DeleteOperationService;
import by.bsuir.flowershop.service.core.base.FindAllPageableOperationService;

public interface ShopService extends
        CustomCrudService<ShopDTO>,
        FindAllPageableOperationService<ShopDTO>,
        DeleteOperationService<ShopDTO> {

}
