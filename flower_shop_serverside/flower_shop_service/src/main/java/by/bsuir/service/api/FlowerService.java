package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface FlowerService extends CustomCrudService<FlowerDTO>, DeleteOperationService<FlowerDTO> {

    PageWrapper<FlowerDTO> findAll(int page, int size, SearchAndSortParamDto searchAndSortParamDto);

}
