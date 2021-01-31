package by.bsuir.controller;

import by.bsuir.dto.model.company.CompanyDTO;


import by.bsuir.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;


@Validated
@RestController
@RequestMapping("/company")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<CompanyDTO> findCompany() {
        CompanyDTO company = companyService.findCompany();
        return ResponseEntity.ok(company);
    }

    //@Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id,
                                                    @RequestBody @Valid CompanyDTO companyDTO,
//                                                    @CurrentUser UserPrincipal userPrincipal,
                                                    BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

//        companyDTO.setAdminId(userPrincipal.getId());

        CompanyDTO company = companyService.update(companyDTO);
        return ResponseEntity.ok(company);
    }

}
