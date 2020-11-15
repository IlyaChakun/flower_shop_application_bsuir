package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.FlowerService;
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

import java.util.List;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;
import static by.bsuir.controller.ControllerHelper.isIdInsideDtoOrThrowException;

@RestController
@RequestMapping("/users/admin/company/{name}/shops/{id}/flowers")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerController {


    private final FlowerService flowerService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FlowerDTO> findById(@PathVariable("id") @PositiveLong String id) {

        FlowerDTO flower = flowerService.findById(Long.valueOf(id));

        return ResponseEntity.ok(flower);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<?> findAllBySearchString(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size
//            @Validated SearchAndSortParamDto searchAndSortParamDto,
//            BindingResult bindingResult
    ) {

//        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

//        PageWrapper<FlowerDTO> wrapper = flowerService.findAll(page - 1, size, searchAndSortParamDto);

        PageWrapper<FlowerDTO> wrapper = flowerService.findAll(page - 1, size, new SearchAndSortParamDto());


        return ResponseEntity.ok(wrapper);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<FlowerDTO> save(@RequestBody @Valid FlowerDTO flowerDTO,
                                          BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        FlowerDTO flower = flowerService.save(flowerDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(flower.getId()).toUri());


        return new ResponseEntity<>(flower, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FlowerDTO> update(@PathVariable("id") @PositiveLong String id,
                                            @RequestBody @Valid FlowerDTO flowerDTO,
                                            BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        isIdInsideDtoOrThrowException(flowerDTO);
        FlowerDTO flower = flowerService.update(flowerDTO);
        return ResponseEntity.ok(flower);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String id) {
        flowerService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
