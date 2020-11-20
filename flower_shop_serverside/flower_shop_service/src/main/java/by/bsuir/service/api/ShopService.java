package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface ShopService extends UpdateOperationService<ShopDTO>, FindOperationService<ShopDTO> {

    ShopDTO save(ShopDTO shopDTO, Long companyId);

    void delete(Long shopId, Long companyId);

    PageWrapper<ShopDTO> findAll(int page, int size);

}
