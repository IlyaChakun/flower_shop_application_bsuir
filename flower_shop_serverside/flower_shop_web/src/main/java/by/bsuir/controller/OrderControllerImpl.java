package by.bsuir.controller;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.BuyNowOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.model.order.usual.UsualOrderDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<PageWrapper<BuyNowOrderDTO>> findAllBuyNowOrders(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            BuyNowOrderSearchCriteriaDTO buyNowOrderSearchCriteriaDTO) {

        PageWrapper<BuyNowOrderDTO> wrapper = orderService.findAll(page - 1, size, buyNowOrderSearchCriteriaDTO);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<UsualOrderDTO>> findAllUsualOrders(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            UsualOrderSearchCriteriaDTO orderSearchCriteriaCriteria) {

        PageWrapper<UsualOrderDTO> wrapper = orderService.findAll(page - 1, size, orderSearchCriteriaCriteria);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<?> findById(@PathVariable("id") @PositiveLong String id) {

        BaseOrderDTO orderDTO = orderService.findById(Long.valueOf(id));

        return ResponseEntity.ok(orderDTO);
    }

    @Override
    public ResponseEntity<?> orderPartialUpdate(@PathVariable("id") @PositiveLong String id,
                                                @RequestBody @Valid OrderPartialUpdate orderPartialUpdate,
                                                BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        orderService.partialUpdate(orderPartialUpdate);

        BaseOrderDTO orderDTO = orderService.findById(Long.valueOf(id));

        return ResponseEntity.ok(orderDTO);
    }

    @Override
    public ResponseEntity<ApiResponse> saveOrder(@RequestBody @Valid UsualOrderDTO usualOrderDTO,
                                                 BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        BaseOrderDTO order = orderService.save(usualOrderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Order registered successfully!"));
    }

    @Override
    public ResponseEntity<ApiResponse> saveBuyNowOrder(@RequestBody @Valid BuyNowOrderDTO buyNowOrderDTO,
                                                       BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        BaseOrderDTO order = orderService.save(buyNowOrderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Order registered successfully!"));
    }
}