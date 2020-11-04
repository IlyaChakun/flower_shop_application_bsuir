package by.bsuir.controller;

import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.service.api.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.util.List;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@Validated
@RestController
@RequestMapping("/user/admin/company/shop")
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> findById(@PathVariable("id") String id,
                                                BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ShopDTO shop = shopService.findById(Long.valueOf(id));

        return ResponseEntity.ok(shop);
    }

    @GetMapping()
    public ResponseEntity<List<ShopDTO>> findAll() {
        return ResponseEntity.ok(shopService.findAll());
    }


    @PostMapping
    public ResponseEntity<ShopDTO> save(@RequestBody @Valid ShopDTO shopDTO,
                                            BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        ShopDTO shop = shopService.save(shopDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shop.getId()).toUri());

        return new ResponseEntity<>(shop, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ShopDTO> update(@PathVariable("id") String id,
                                              @RequestBody @Valid ShopDTO shopDTO,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        shopDTO.setId(Long.valueOf(id));
        ShopDTO shop = shopService.update(shopDTO);
        return ResponseEntity.ok(shop);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        shopService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
