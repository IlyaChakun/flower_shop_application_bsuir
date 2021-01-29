package by.bsuir.flowershop.service;

import by.bsuir.flowershop.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.flowershop.dto.model.company.CompanyDTO;
import by.bsuir.flowershop.entity.company.Company;
import by.bsuir.flowershop.repository.api.company.CompanyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO findCompany() {
        logger.info("search company ");
        return companyMapper.toDto(companyRepository.findFirstBy());
    }


    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO companyDTO) {

        Company company = companyRepository.findFirstBy();

        Company companyToSave = companyMapper.toEntity(companyDTO);
        companyToSave.setId(company.getId());
        companyToSave.setDateOfCreation(company.getDateOfCreation());
        companyToSave.setAdminId(company.getAdminId());

        return companyMapper.toDto(companyRepository.save(companyToSave));
    }

}
