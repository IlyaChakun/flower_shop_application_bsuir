package by.bsuir.controller;

import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.service.api.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@Validated
@RestController
@RequestMapping("/user/client")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {


    private final ClientService clientService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{email}")
    public ResponseEntity<ClientDTO> updateClientProfile(@PathVariable("email") String email,
                                                    @RequestBody @Valid ClientDTO clientDTO,
                                                    BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        ClientDTO client = clientService.update(clientDTO, email);
        return ResponseEntity.ok(client);
    }


}
