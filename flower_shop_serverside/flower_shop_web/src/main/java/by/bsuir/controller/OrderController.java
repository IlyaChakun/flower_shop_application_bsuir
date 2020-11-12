package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.ClientService;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@Validated
@RestController
@RequestMapping("/user/client/{clientId}/order")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final ClientService cLientService;

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("clientId") String clientId,
                                             @PathVariable("id") String id) {

        OrderDTO orderDTO = orderService.findByIdAndClientId(Long.valueOf(id), Long.valueOf(clientId));

        return ResponseEntity.ok(orderDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@PathVariable("clientId") String clientId,
                                              @RequestBody @Valid OrderDTO orderDTO,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        orderDTO.getOrderInfo().getClient().setId(Long.valueOf(clientId));

        OrderDTO order = orderService.save(orderDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri());

        return new ResponseEntity<>(order, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable("clientId") String clientId,
                                           @PathVariable("id") String id,
                                           @RequestBody @Valid OrderDTO orderDTO,
                                           BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        orderDTO.getOrderInfo().getClient().setId(Long.valueOf(clientId));
        orderDTO.setId(Long.valueOf(id));
        OrderDTO order = orderService.update(orderDTO);
        return ResponseEntity.ok(order);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping()
    public ResponseEntity<PageWrapper<OrderDTO>> findAll(@PathVariable("id") String id,
                                                         @RequestParam(defaultValue = "1", required = false) Integer page,
                                                         @RequestParam(defaultValue = "10", required = false) Integer size) {
        PageWrapper<OrderDTO> wrapper = orderService.findAllByClientId(page - 1, size, Long.valueOf(id));
        return ResponseEntity.ok(wrapper);
    }
}
