package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.core.CurrentUser;
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
import static by.bsuir.controller.ControllerHelper.isIdInsideDtoOrThrowException;

@Validated
@RestController//users/clients/
@RequestMapping("/user/clients/order")//TODO так просто  не получится.. Да и не забываем писать во множ числе
//тут ты должден понимать что под клиентом не нужно указывать ид ( то есть я смотрю свои заказы и у меня урл user/client/orders+ {id} если конкртный
//или если он смотрти заказы в конкретном магазине там user/admin/company/{name}/shops/{id}/orders + {id} если конкретный
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
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO orderDTO,
                                              @CurrentUser UserPrincipal userPrincipal,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ClientDTO clientDTO = getClient(userPrincipal);

        orderDTO.getOrderInfo().setClient(clientDTO);

        OrderDTO order = orderService.save(orderDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri());

        return new ResponseEntity<>(order, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable("id") @PositiveLong String id,
                                           @RequestBody @Valid OrderDTO orderDTO,
                                           @CurrentUser UserPrincipal userPrincipal,
                                           BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        isIdInsideDtoOrThrowException(orderDTO);

        ClientDTO clientDTO = getClient(userPrincipal);

        orderDTO.getOrderInfo().setClient(clientDTO);
        OrderDTO order = orderService.update(orderDTO);

        return ResponseEntity.ok(order);
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
