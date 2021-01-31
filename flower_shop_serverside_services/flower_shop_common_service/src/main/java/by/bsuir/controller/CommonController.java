package by.bsuir.controller;

import by.bsuir.dto.CountryDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CommonController {

    private final CommonService commonService;


    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryDTO> findById(@PathVariable("id") @PositiveLong String id) {

        CountryDTO categoryDTO = commonService.getOneCountry(Long.valueOf(id));

        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> findAll() {

        List<CountryDTO> categories = commonService.findAllCountries();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/countries/exist-by-id/{id}")//TODO rename
    public ResponseEntity<Boolean> existById(@PathVariable("id") @PositiveLong String id) {

        Boolean isExist = commonService.existById(Long.valueOf(id));

        return ResponseEntity.ok(isExist);
    }
}
