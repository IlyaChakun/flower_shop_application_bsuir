package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface FlowerTypeService extends CustomCrudService<FlowerTypeDTO>, DeleteOperationService<FlowerTypeDTO> {

    PageWrapper<FlowerTypeDTO> findAll(int page, int size);

}
