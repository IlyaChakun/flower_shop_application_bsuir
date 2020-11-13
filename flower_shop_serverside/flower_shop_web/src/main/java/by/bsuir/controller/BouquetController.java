package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.FlowerBouquetService;
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
import static by.bsuir.controller.ControllerHelper.checkIdInsideDto;


@RestController
@RequestMapping("/user/admin/company/shops/{id}/bouquets")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BouquetController {

    private final FlowerBouquetService flowerBouquetService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FlowerBouquetDTO> findById(@PathVariable("id") @PositiveLong String id) {

        FlowerBouquetDTO flowerBouquet = flowerBouquetService.findById(Long.valueOf(id));//TODO давай напишем либо аннотацию ValidLong ( я псал как то ) суть в том чтобы кинуть 400 ошибку если там все таки строка а не число а то будет плохо

        return ResponseEntity.ok(flowerBouquet);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<?> findAllBySearchString(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @Validated SearchAndSortParamDto searchAndSortParamDto,
            BindingResult bindingResult) {

        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        PageWrapper<FlowerBouquetDTO> wrapper = flowerBouquetService.findAll(page - 1, size, searchAndSortParamDto);

        return ResponseEntity.ok(wrapper);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<FlowerBouquetDTO> save(@RequestBody @Valid FlowerBouquetDTO flowerBouquetDTO,
                                                 BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        FlowerBouquetDTO flowerBouquet = flowerBouquetService.save(flowerBouquetDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")//TODO а где путь для findByid?
                .buildAndExpand(flowerBouquet.getId()).toUri());


        return new ResponseEntity<>(flowerBouquet, httpHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FlowerBouquetDTO> update(@PathVariable("id") @PositiveLong String id,
                                                   @RequestBody @Valid FlowerBouquetDTO flowerBouquetDTO,
                                                   BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        checkIdInsideDto(flowerBouquetDTO);
//        flowerBouquetDTO.setId(Long.valueOf(id));//TODO так не надо, по урлу наебать можно систесу, надо надеяться что пришел ид внутри сущности, а в урле он прпросто для читаемости

        FlowerBouquetDTO flowerBouquet = flowerBouquetService.update(flowerBouquetDTO);
        return ResponseEntity.ok(flowerBouquet);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String id) {
        flowerBouquetService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
