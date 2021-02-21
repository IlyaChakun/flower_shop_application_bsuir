package by.bsuir.controller;

import by.bsuir.dto.FloristDTO;
import by.bsuir.dto.FloristRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/florists")
public interface FloristController {

    @PostMapping
    ResponseEntity<FloristDTO> save(@RequestBody @Valid FloristRequestDTO floristRequestDTO,
                                    BindingResult result);

}
