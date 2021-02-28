package by.bsuir.controller;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.dto.model.florist.FloristRequestDTO;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.service.api.FloristService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@AllArgsConstructor
public class FloristControllerImpl implements FloristController {

    private final FloristService floristService;

    @Override
    public ResponseEntity<FloristDTO> save(@Valid FloristRequestDTO floristRequestDTO, BindingResult result) {

        checkBindingResultAndThrowExceptionIfInvalid(result);

        FloristDTO addedFlorist = floristService.save(floristRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/florists/{id}")
                .buildAndExpand(addedFlorist.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(addedFlorist);
    }

    @Override
    public ResponseEntity<FloristDTO> update(@PathVariable("id") @PositiveLong String id,
                                             @Valid FloristDTO floristDTO, BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        FloristDTO updatedFlorist = floristService.update(floristDTO);
        return ResponseEntity.ok(updatedFlorist);
    }

    @Override
    public ResponseEntity<FloristDTO> findById(String id) {
        FloristDTO floristDTO = floristService.findById(Long.valueOf(id));

        return ResponseEntity.ok(floristDTO);
    }

    @Override
    public ResponseEntity<?> findAll(Integer page, Integer size) {

        PageWrapper<FloristDTO> wrapper = floristService.findAll(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }
}
