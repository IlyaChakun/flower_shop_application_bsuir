package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public interface OrderController {

    @GetMapping
    ResponseEntity<PageWrapper<OrderDTO>> findAll(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            UsualOrderSearchCriteriaDTO orderSearchCriteriaCriteria);

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") @PositiveLong String id);

    @PatchMapping("/{id}")
    ResponseEntity<?> orderPartialUpdate(@PathVariable("id") @PositiveLong String id,
                                         @RequestBody @Valid OrderPartialUpdate orderPartialUpdate,
                                         BindingResult result);

    @PostMapping
    ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO usualOrderDTO,
                                          BindingResult result);

    @PostMapping("/buy-now-orders")
    ResponseEntity<OrderDTO> saveBuyNowOrder(@RequestBody @Valid BuyNowOrderDTO buyNowOrderDTO,
                                                BindingResult result);
}
