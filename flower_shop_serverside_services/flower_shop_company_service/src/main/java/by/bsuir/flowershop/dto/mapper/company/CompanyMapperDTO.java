package by.bsuir.flowershop.dto.mapper.company;

import by.bsuir.flowershop.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.flowershop.dto.model.company.CompanyDTO;
import by.bsuir.flowershop.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyMapperDTO extends AbstractMapperDTO<Company, CompanyDTO> {

    @Autowired
    public CompanyMapperDTO() {
        super(Company.class, CompanyDTO.class);
    }
}