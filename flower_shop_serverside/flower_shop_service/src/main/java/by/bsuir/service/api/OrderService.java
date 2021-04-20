package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.OrderDetailDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveOperationService;

public interface OrderService extends
        SaveOperationService<OrderDTO>,
        FindOperationService<OrderDTO> {

    void partialUpdate(OrderPartialUpdate partialUpdate);

    OrderDetailDTO findOrderDetailById(Long id);

    PageWrapper<OrderDTO> findAll(int page, int size, UsualOrderSearchCriteriaDTO searchParams);

}
