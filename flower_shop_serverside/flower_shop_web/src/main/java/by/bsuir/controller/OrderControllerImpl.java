package by.bsuir.controller;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.OrderDetailDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.OrderService;
import java.net.URI;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;


    @Override
    public ResponseEntity<PageWrapper<OrderDTO>> findAll(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            UsualOrderSearchCriteriaDTO orderSearchCriteriaCriteria) {

        PageWrapper<OrderDTO> wrapper = orderService.findAll(page - 1, size, orderSearchCriteriaCriteria);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<?> findById(@PathVariable("id") @PositiveLong String id) {

        OrderDetailDTO orderDetailDTO = orderService.findOrderDetailById(Long.valueOf(id));
        return ResponseEntity.ok(orderDetailDTO);
    }

    @Override
    public ResponseEntity<?> orderPartialUpdate(@PathVariable("id") @PositiveLong String id,
                                                @RequestBody @Valid OrderPartialUpdate orderPartialUpdate,
                                                BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        orderService.partialUpdate(orderPartialUpdate);

        OrderDTO orderDTO = orderService.findById(Long.valueOf(id));

        return ResponseEntity.ok(orderDTO);
    }

    @Override
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO usualOrderDTO,
                                              BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        OrderDTO order = orderService.save(usualOrderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(order);
    }

    @Override
    public ResponseEntity<OrderDTO> saveBuyNowOrder(@RequestBody @Valid BuyNowOrderDTO buyNowOrderDTO,
                                                       BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        OrderDTO order = orderService.save(buyNowOrderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(order);
    }
}
