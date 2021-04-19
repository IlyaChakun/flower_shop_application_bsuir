package by.bsuir.controller;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientControllerImpl implements ClientController {
    private final UserService userService;

    @Override
    public ResponseEntity<?> findAll(Integer page, Integer size) {
        PageWrapper<UserDTO> wrapper = userService.findAllClients(page - 1, size);

        return ResponseEntity.ok(wrapper);
    }
}
