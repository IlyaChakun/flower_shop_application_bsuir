package by.bsuir.service;


import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.service.core.base.UpdateOperationService;

public interface CompanyService extends UpdateOperationService<CompanyDTO> {

    CompanyDTO findCompany();

}
