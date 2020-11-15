package by.bsuir.controller;


import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
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
@RequestMapping("/company")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerCommonController {

    private final ProductCommonService productCommonService;


    @GetMapping("/flower-types")
    public ResponseEntity<?> findAllFlowerTypes() {
        List<FlowerTypeDTO> flowerTypes = productCommonService.findAllFlowerTypes();
        return ResponseEntity.ok(flowerTypes);
    }

    @GetMapping("/flower-bouquet-types")
    public ResponseEntity<?> findAllFlowerBouquetTypes() {
        List<BouquetTypeDTO> flowerBouquetTypes = productCommonService.findAllFlowerBouquetTypes();
        return ResponseEntity.ok(flowerBouquetTypes);
    }
}
