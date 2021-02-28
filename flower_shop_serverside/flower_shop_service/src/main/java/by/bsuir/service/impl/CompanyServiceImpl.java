package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.repository.api.company.CompanyRepository;
import by.bsuir.service.api.CompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO findCompany() {
        log.info("search company ");
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
