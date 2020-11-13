package by.bsuir.service.api;

import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

import java.util.List;

public interface BouquetTypeService extends CustomCrudService<BouquetTypeDTO>, DeleteOperationService<BouquetTypeDTO> {

    List<BouquetTypeDTO> findAll();

}
