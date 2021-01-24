package by.bsuir.controller;


import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.model.order.usual.UsualOrderDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
//@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;


//    @GetMapping("/orders")
//    public ResponseEntity<?> findAllProducts(
//            @RequestParam(defaultValue = "1", required = false) Integer page,
//            @RequestParam(defaultValue = "10", required = false) Integer size) {
//
//        PageWrapper<BaseOrderDTO> wrapper = orderService.findAll(page - 1, size, null);
//
//        return ResponseEntity.ok(wrapper);
//    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") @PositiveLong String id) {

        BaseOrderDTO orderDTO = orderService.findById(Long.valueOf(id));

        return ResponseEntity.ok(orderDTO);
    }


    @PostMapping("/orders")
    //@Secured("ROLE_CLIENT")
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

    @PostMapping("/buy-now-orders")
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


    @PatchMapping("/orders/{id}")
    public ResponseEntity<?> orderPartialUpdate(@PathVariable("id") @PositiveLong String id,
                                                @RequestBody @Valid OrderPartialUpdate orderPartialUpdate,
                                                BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        orderService.partialUpdate(orderPartialUpdate);

        BaseOrderDTO orderDTO = orderService.findById(Long.valueOf(id));

        return ResponseEntity.ok(orderDTO);
    }
}
