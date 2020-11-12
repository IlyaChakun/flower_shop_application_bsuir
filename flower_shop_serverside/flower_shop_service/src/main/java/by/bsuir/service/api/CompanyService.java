package by.bsuir.service.api;


import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveOperationService;

public interface CompanyService extends SaveOperationService<CompanyDTO>, FindOperationService<CompanyDTO> {

    CompanyDTO findByName(String name);

    CompanyDTO update(CompanyDTO company, String name);
}
