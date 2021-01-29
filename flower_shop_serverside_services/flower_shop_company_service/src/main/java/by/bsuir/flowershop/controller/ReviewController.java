package by.bsuir.flowershop.controller;

import by.bsuir.flowershop.dto.model.PageWrapper;
import by.bsuir.flowershop.dto.model.review.ReviewDTO;
import by.bsuir.flowershop.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static by.bsuir.flowershop.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/company/reviews")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody @Valid ReviewDTO reviewDTO,
                                          BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ReviewDTO review = reviewService.save(reviewDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(review.getId()).toUri());


        return new ResponseEntity<>(review, httpHeaders, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageWrapper<ReviewDTO> wrapper = reviewService.findAll(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }

}
