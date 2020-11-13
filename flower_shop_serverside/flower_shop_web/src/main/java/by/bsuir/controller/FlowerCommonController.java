package by.bsuir.controller;


import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.api.FlowerCommonService;
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
@RequestMapping("/company")//TODO просто company
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerCommonController {

    private final FlowerCommonService flowerCommonService;


    //TODO не админ а для всех, ведь это же можно получать просто для каталога поиск и тд
    @GetMapping("/flower-types")
    public ResponseEntity<?> findAllFlowerTypes() {

        List<FlowerTypeDTO> flowerTypes = flowerCommonService.findAllFlowerTypes();

        return ResponseEntity.ok(flowerTypes);
    }

    //TODO тоже самое
    @GetMapping("/flower-bouquet-types")
    public ResponseEntity<?> findAllFlowerBouquetTypes() {

        List<BouquetTypeDTO> flowerBouquetTypes = flowerCommonService.findAllFlowerBouquetTypes();

        return ResponseEntity.ok(flowerBouquetTypes);
    }
}

