package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface BouquetTypeService extends CustomCrudService<BouquetTypeDTO>, DeleteOperationService<BouquetTypeDTO> {

    PageWrapper<BouquetTypeDTO> findAll(int page, int size);

}
