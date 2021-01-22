package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.shop.ShopDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.OrderService;
import by.bsuir.service.api.ShopService;
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
@RestController
@RequestMapping("/shops")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ShopController {

    private final ShopService shopService;
    private final OrderService orderService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> findById(@PathVariable("id") @PositiveLong String id) {

        ShopDTO shop = shopService.findById(Long.valueOf(id));
        return ResponseEntity.ok(shop);
    }

    @GetMapping()
    public ResponseEntity<PageWrapper<ShopDTO>> findAll(@RequestParam(defaultValue = "1", required = false) Integer page,
                                                        @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<ShopDTO> wrapper = shopService.findAll(page - 1, size);
        return ResponseEntity.ok(wrapper);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ShopDTO> saveShopToCompany(@RequestBody @Valid ShopDTO shopDTO,
                                                     BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ShopDTO shop = shopService.save(shopDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shop.getId()).toUri());

        return new ResponseEntity<>(shop, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ShopDTO> update(@PathVariable("id") @PositiveLong String id,
                                          @RequestBody @Valid ShopDTO shopDTO,
                                          BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        isIdInsideDtoOrThrowException(shopDTO);

        ShopDTO shop = shopService.update(shopDTO);
        return ResponseEntity.ok(shop);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String id) {

        shopService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
