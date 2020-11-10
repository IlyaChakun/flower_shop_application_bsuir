package by.bsuir.service.api;

import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface FlowerService extends CustomCrudService<FlowerDTO>, DeleteOperationService<FlowerDTO> {
}
