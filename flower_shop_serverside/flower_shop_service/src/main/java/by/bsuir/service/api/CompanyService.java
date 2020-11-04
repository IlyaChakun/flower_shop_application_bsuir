package by.bsuir.service.api;


import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.service.core.CustomCrudService;

public interface CompanyService extends CustomCrudService<CompanyDTO> {

    CompanyDTO findByName(String name);
}
