package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.mapper.user.ClientMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Shop;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.core.CompanyRepository;
import by.bsuir.service.api.CompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    @Transactional
    public CompanyDTO save(CompanyDTO companyDTO) {
        logger.info("Save company: " + companyDTO);

        return companyMapper.toDto(companyRepository.save(companyMapper.toEntity(companyDTO)));
    }

    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO companyDTO) {
        logger.info("update company: " + companyDTO);

        Company company = companyMapper.toEntity(companyDTO);

        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Company with id=" + id + " not found!");
                            return new ResourceNotFoundException("Company with id=" + id + " not found!");
                        }
                );

        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDTO findByName(String name) {
        logger.info("search by company name: " + name);

        return companyMapper.toDto(companyRepository.findByName(name));
    }
}
