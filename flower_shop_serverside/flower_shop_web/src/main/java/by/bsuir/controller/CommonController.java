package by.bsuir.controller;

import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.dto.model.order.delivery.DeliveryTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/common")
@CrossOrigin(origins = "*")
public interface CommonController {

    @GetMapping("/product-lengths")
    ResponseEntity<List<ProductLengthDTO>> findAllProductLength();

    @GetMapping("/countries")
    ResponseEntity<List<CountryDTO>> findAllCountries();

    @GetMapping("/countries")
    ResponseEntity<List<DeliveryTypeDTO>> findAllDeliveryTypes();

}
