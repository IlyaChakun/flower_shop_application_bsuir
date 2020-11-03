package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.mapper.user.ClientMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.repository.api.core.CompanyRepository;
import by.bsuir.service.api.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO getOne(Long id) {
        return companyMapper.toDto(companyRepository.getOne(id));
    }

    @Override
    @Transactional
    public CompanyDTO save(CompanyDTO companyDTO) {
        return companyMapper.toDto(companyRepository.save(companyMapper.toEntity(companyDTO)));
    }

    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO companyDTO) {

        Company company = companyMapper.toEntity(companyDTO);

//        Company companyPrev = companyRepository.fi

        return null;
    }
}
