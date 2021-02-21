package by.bsuir.controller;

import by.bsuir.dto.FloristDTO;
import by.bsuir.dto.FloristRequestDTO;
import by.bsuir.service.FloristService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
}
