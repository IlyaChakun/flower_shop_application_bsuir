package by.bsuir.controller;


import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.api.ProductCommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/common")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerCommonController {

    private final ProductCommonService productCommonService;


    @GetMapping("/flower-types")
    public ResponseEntity<?> findAllFlowerTypes() {
        List<FlowerTypeDTO> list = productCommonService.findAllFlowerTypes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/flower-bouquet-types")
    public ResponseEntity<?> findAllFlowerBouquetTypes() {
        List<BouquetTypeDTO> list = productCommonService.findAllFlowerBouquetTypes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/flower-colors")
    public ResponseEntity<?> findAllFlowerColors() {
        List<FlowerColorDTO> list = productCommonService.findAllFlowerColors();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/flower-sorts")
    public ResponseEntity<?> findAllFlowerSorts() {
        List<FlowerSortDTO> list = productCommonService.findAllFlowerSorts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> findAllCountries() {
        List<CountryDTO> list = productCommonService.findAllCountries();
        return ResponseEntity.ok(list);
    }


}
