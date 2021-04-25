package by.bsuir.controller;

import by.bsuir.dto.model.common.CityDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.dto.model.order.delivery.DeliveryTypeDTO;
import by.bsuir.service.api.CommonService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ResponseEntity<List<CityDTO>> findAllCities() {
        List<CityDTO> objects = commonService.findAllCities();
        return ResponseEntity.ok(objects);
    }

    @Override
    public ResponseEntity<List<DeliveryTypeDTO>> findAllDeliveryTypes() {
        List<DeliveryTypeDTO> objects = commonService.findAllDeliveryTypes();
        return ResponseEntity.ok(objects);
    }
}
