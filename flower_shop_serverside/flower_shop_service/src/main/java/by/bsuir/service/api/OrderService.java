package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.core.base.UpdateOperationService;

public interface OrderService extends UpdateOperationService<OrderDTO> {


    OrderDTO save(OrderDTO orderDTO);

    OrderDTO findByIdAndClientId(Long orderId, Long userId);

    PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long userId);

}
