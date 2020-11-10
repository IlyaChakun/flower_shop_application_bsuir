package by.bsuir.controller;

import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.FlowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/user/admin/company/shops/{id}/flowers")
@AllArgsConstructor
@CrossOrigin(origins = "localhost:/3000")
public class FlowerController {


    private final FlowerService flowerService;


    @GetMapping("/{id}")
    public ResponseEntity<FlowerDTO> findById(@PathVariable("id") String id,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        FlowerDTO flower = flowerService.findById(Long.valueOf(id));

        return ResponseEntity.ok(flower);
    }


    @PostMapping
    public ResponseEntity<FlowerDTO> save(@RequestBody @Valid FlowerDTO flowerDTO,
                                                  @CurrentUser UserPrincipal userPrincipal,
                                                  BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        FlowerDTO company = flowerService.save(flowerDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{}")
                .buildAndExpand(company.getId()).toUri());


        return new ResponseEntity<>(company, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FlowerDTO> update(@PathVariable("id") String id,
                                                    @RequestBody @Valid FlowerDTO flowerDTO,
                                                    BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        FlowerDTO flower = flowerService.update(flowerDTO);
        return ResponseEntity.ok(flower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        flowerService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
