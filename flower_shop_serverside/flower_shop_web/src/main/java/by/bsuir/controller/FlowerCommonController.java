package by.bsuir.controller;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.api.FlowerCommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user/admin/company")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FlowerCommonController {

    private final FlowerCommonService flowerCommonService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/flower-types")
    public ResponseEntity<?> findAllFlowerTypes(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<FlowerTypeDTO> wrapper = flowerCommonService.findAllFlowerTypes(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/flower-bouquet-types")
    public ResponseEntity<?> findAllFlowerBouquetTypes(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<BouquetTypeDTO> wrapper = flowerCommonService.findAllFlowerBouquetTypes(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }
}

