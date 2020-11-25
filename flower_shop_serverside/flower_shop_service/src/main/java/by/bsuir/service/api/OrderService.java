package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.OrderRequestDTO;

public interface OrderService {

    OrderDTO saveOrder(OrderRequestDTO orderRequest);

    OrderDTO findByIdAndClientId(Long orderId, Long userId);

    PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long userId);

}
