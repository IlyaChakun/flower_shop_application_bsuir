package by.bsuir.controller;

import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.service.api.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/user/admin/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompanyById(@PathParam("id") String id,
                                                      BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CompanyDTO company = companyService.getOne(Long.valueOf(id));

        return ResponseEntity.ok(company);
    }


    @PostMapping
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody @Valid CompanyDTO companyDTO,
                                                  BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CompanyDTO company = companyService.save(companyDTO);

        return ResponseEntity.ok(company);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathParam("id") String id,
                                                    @RequestBody @Valid CompanyDTO companyDTO,
                                                    BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CompanyDTO company = companyService.update(companyDTO);

        return ResponseEntity.ok(company);
    }

}
