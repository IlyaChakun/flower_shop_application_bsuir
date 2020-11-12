package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface FlowerBouquetService extends CustomCrudService<FlowerBouquetDTO>, DeleteOperationService<FlowerBouquetDTO> {

    PageWrapper<FlowerBouquetDTO> findAll(int page, int size, SearchAndSortParamDto searchAndSortParamDto);

}
