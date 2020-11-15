package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.core.base.SaveOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface OrderService extends SaveOperationService<OrderDTO>, UpdateOperationService<OrderDTO> {

    OrderDTO findByIdAndClientId(Long orderId, Long userId);

    PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long userId);

}
