package by.bsuir.service.api;


import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveOperationService;

public interface OrderService extends
        SaveOperationService<BaseOrderDTO>,
        FindOperationService<BaseOrderDTO> {

    void partialUpdate(OrderPartialUpdate partialUpdate);

}
