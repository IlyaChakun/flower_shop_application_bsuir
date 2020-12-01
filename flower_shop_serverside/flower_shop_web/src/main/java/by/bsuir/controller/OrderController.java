package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.OrderRequestDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.ClientService;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@Validated
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final ClientService cLientService;

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") @PositiveLong String id,
                                             @CurrentUser UserPrincipal userPrincipal) {

        ClientDTO clientDTO = getClient(userPrincipal);

        OrderDTO orderDTO = orderService.findByIdAndClientId(Long.valueOf(id), clientDTO.getId());

        return ResponseEntity.ok(orderDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderRequestDTO orderRequest,
                                              @CurrentUser UserPrincipal userPrincipal,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        orderRequest.setClientId(userPrincipal.getId());

        OrderDTO order = orderService.saveOrder(orderRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri());

        return new ResponseEntity<>(order, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping()
    public ResponseEntity<PageWrapper<OrderDTO>> findAll(@CurrentUser UserPrincipal userPrincipal,
                                                         @RequestParam(defaultValue = "1", required = false) Integer page,
                                                         @RequestParam(defaultValue = "10", required = false) Integer size) {

        ClientDTO clientDTO = getClient(userPrincipal);

        PageWrapper<OrderDTO> wrapper = orderService.findAllByClientId(page - 1, size, clientDTO.getId());
        return ResponseEntity.ok(wrapper);
    }


    private ClientDTO getClient(UserPrincipal userPrincipal) {
        final String userEmail = userPrincipal.getEmail();
        return cLientService.findByEmail(userEmail);
    }
}
