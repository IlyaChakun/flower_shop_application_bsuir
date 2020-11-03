package by.bsuir.controller;

import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.security.core.TokenProvider;
import by.bsuir.security.dto.AuthTokenResponse;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/user/admin/company")
@AllArgsConstructor
public class CompanyController {

    private final UserSecurityService userService;
    private final CompanyService companyService;
    private final TokenProvider tokenProvider;


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompanyById(@PathParam("id") String id,
                                                             BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        CompanyDTO company = companyService.findById(Long.valueOf(id));

        return ResponseEntity.ok(company);
    }


}
