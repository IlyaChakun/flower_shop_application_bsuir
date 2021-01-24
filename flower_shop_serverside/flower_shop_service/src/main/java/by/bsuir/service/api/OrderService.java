package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.BuyNowOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.model.order.usual.UsualOrderDTO;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveOperationService;

public interface OrderService extends
        SaveOperationService<BaseOrderDTO>,
        FindOperationService<BaseOrderDTO> {

    void partialUpdate(OrderPartialUpdate partialUpdate);

    PageWrapper<UsualOrderDTO> findAll(int page, int size, UsualOrderSearchCriteriaDTO searchParams);

    PageWrapper<BuyNowOrderDTO> findAll(int page, int size, BuyNowOrderSearchCriteriaDTO searchParams);

}
