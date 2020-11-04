package by.bsuir.controller;

import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.CompanyService;
import by.bsuir.service.api.ShopAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@Validated
@RestController
@RequestMapping("/user/admin/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final ShopAdminService shopAdminService;


    @GetMapping("/{name}")
    public ResponseEntity<CompanyDTO> findCompanyByName(@PathVariable("name") String name,
                                                        BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CompanyDTO company = companyService.findByName(name);

        return ResponseEntity.ok(company);
    }


    @PostMapping
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody @Valid CompanyDTO companyDTO,
                                                  @CurrentUser UserPrincipal userPrincipal,
                                                  BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final String userEmail = userPrincipal.getEmail();
        ShopAdminDTO shopAdminDTO = shopAdminService.findByEmail(userEmail);
        companyDTO.setShopAdmin(shopAdminDTO);


        CompanyDTO company = companyService.save(companyDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
                .buildAndExpand(company.getId()).toUri());


        return new ResponseEntity<>(company, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping("/{name}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("name") String name,
                                                    @RequestBody @Valid CompanyDTO companyDTO,
                                                    BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        CompanyDTO company = companyService.update(companyDTO);
        return ResponseEntity.ok(company);
    }


}
