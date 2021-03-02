package by.bsuir.controller;

import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.service.api.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommonControllerImpl implements CommonController {


    private final CommonService commonService;


    @Override
    public ResponseEntity<List<ProductLengthDTO>> findAllProductLength() {
        List<ProductLengthDTO> objects = commonService.findAllProductLengths();

        return ResponseEntity.ok(objects);
    }

    @Override
    public ResponseEntity<List<CountryDTO>> findAllCountries() {
        List<CountryDTO> objects = commonService.findAllCountries();

        return ResponseEntity.ok(objects);
    }
}
