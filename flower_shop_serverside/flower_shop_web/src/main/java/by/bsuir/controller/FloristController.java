package by.bsuir.controller;

import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.dto.model.florist.FloristRequestDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/florists")
@CrossOrigin(origins = "*")
public interface FloristController {

    @PostMapping
    ResponseEntity<FloristDTO> save(@RequestBody @Valid FloristRequestDTO floristRequestDTO,
                                    BindingResult result);

    @PutMapping("/{id}")
    ResponseEntity<FloristDTO> update(@PathVariable("id") @PositiveLong String id,
                                      @RequestBody @Valid FloristDTO floristDTO,
                                      BindingResult result);

    @GetMapping("/{id}")
    ResponseEntity<FloristDTO> findById(@PathVariable("id") @PositiveLong String id);

    @GetMapping
    ResponseEntity<?> findAll(@RequestParam(defaultValue = "1", required = false) Integer page,
                              @RequestParam(defaultValue = "10", required = false) Integer size);

}
