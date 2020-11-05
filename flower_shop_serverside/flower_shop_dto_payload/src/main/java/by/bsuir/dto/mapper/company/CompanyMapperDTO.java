package by.bsuir.dto.mapper.company;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperDTO extends AbstractMapperDTO<Company, CompanyDTO> {

    @Autowired
    public CompanyMapperDTO() {
        super(Company.class, CompanyDTO.class);
    }
}