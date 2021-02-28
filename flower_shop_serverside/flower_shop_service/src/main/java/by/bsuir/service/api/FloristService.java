package by.bsuir.service.api;

import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.dto.model.florist.FloristRequestDTO;
import by.bsuir.service.core.base.FindAllPageableOperationService;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface FloristService extends UpdateOperationService<FloristDTO>,
        FindOperationService<FloristDTO>,
        FindAllPageableOperationService<FloristDTO> {

    FloristDTO save(FloristRequestDTO floristRequestDTO);

}
