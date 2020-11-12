package by.bsuir.service.api;


import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.FindAllOperationService;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface ShopService extends
        UpdateOperationService<ShopDTO>,
        FindOperationService<ShopDTO>,
        FindAllOperationService<ShopDTO> {

    ShopDTO save(ShopDTO shopDTO, String companyName);

    void delete(Long shopId, String companyName);

}
