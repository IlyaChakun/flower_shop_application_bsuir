package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.service.core.CustomCrudService;
import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.FindAllPageableSortableOperationService;

public interface OrderService extends
        CustomCrudService<ProductDTO>,
        DeleteOperationService<ProductDTO>,
        FindAllPageableSortableOperationService<ProductDTO> {

//TODO
//    OrderDTO saveOrder(OrderRequestDTO orderRequest);

    OrderDTO findByIdAndClientId(Long orderId, Long userId);

    PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long userId);
//TODO
//    PageWrapper<OrderDTO> findAllByShopId(int page, int size, Long shopId);
}
