package by.bsuir.controller;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.api.FlowerCommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/company")//TODO просто company
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerCommonController {

    private final FlowerCommonService flowerCommonService;


    //TODO не админ а для всех, ведь это же можно получать просто для каталога поиск и тд
    @GetMapping("/flower-types")
    public ResponseEntity<?> findAllFlowerTypes(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<FlowerTypeDTO> wrapper = flowerCommonService.findAllFlowerTypes(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }

    //TODO тоже самое
    @GetMapping("/flower-bouquet-types")
    public ResponseEntity<?> findAllFlowerBouquetTypes(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<BouquetTypeDTO> wrapper = flowerCommonService.findAllFlowerBouquetTypes(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }
}

