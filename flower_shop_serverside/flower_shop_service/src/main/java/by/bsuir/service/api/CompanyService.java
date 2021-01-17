package by.bsuir.service.api;


import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.service.core.base.GetOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface CompanyService extends UpdateOperationService<CompanyDTO>, GetOperationService<CompanyDTO> {

}
