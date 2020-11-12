package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;

public interface OrderService extends
        CustomCrudService<OrderDTO>,
        DeleteOperationService<OrderDTO> {

    PageWrapper<OrderDTO> findAll(int page, int size);

}
