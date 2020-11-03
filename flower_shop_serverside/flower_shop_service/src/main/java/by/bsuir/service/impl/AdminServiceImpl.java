package by.bsuir.service.impl;

import by.bsuir.service.api.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public Optional<AdminDTO> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public AdminDTO getByEmail(String email) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }
}
