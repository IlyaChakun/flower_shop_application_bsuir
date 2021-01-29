package by.bsuir.flowershop.service;


import by.bsuir.flowershop.dto.model.company.CompanyDTO;
import by.bsuir.flowershop.service.core.base.UpdateOperationService;

public interface CompanyService extends UpdateOperationService<CompanyDTO> {

    CompanyDTO findCompany();

}
