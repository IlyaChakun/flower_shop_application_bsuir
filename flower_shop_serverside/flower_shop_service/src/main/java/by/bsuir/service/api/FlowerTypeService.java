package by.bsuir.service.api;

import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

import java.util.List;

public interface FlowerTypeService extends CustomCrudService<FlowerTypeDTO>, DeleteOperationService<FlowerTypeDTO> {

    List<FlowerTypeDTO> findAll();

}
