package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.user.ShopAdmin;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.CompanyRepository;
import by.bsuir.repository.api.user.ShopAdminRepository;
import by.bsuir.service.api.CompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;
    private final ShopAdminRepository shopAdminRepository;


    @Override
    @Transactional
    public CompanyDTO save(CompanyDTO companyDTO) {
        if (isCompanyExistByName(companyDTO.getName())) {
            logger.error("Company with name={} exist. Just Update it!", companyDTO.getName());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "company_already_exists",
                    "Company with name=" + companyDTO.getName() + " exists. Just Update it!");
        }
        ShopAdmin shopAdmin = shopAdminRepository.getOne(companyDTO.getShopAdmin().getId());

        if (Objects.nonNull(shopAdmin.getCompany())) {
            logger.error("You try to add another one company! It is not possible. Only one company may exist. " +
                    "And you have one with name={} already exists. Just Update it!", shopAdmin.getCompany().getName());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "company_already_exists",
                    "You try to add another one company! It is not possible. Only one company may exist. " +
                            "And you have one with name=" + shopAdmin.getCompany().getName() + " already exists. Just Update it!");
        }

        Company company = companyMapper.toEntity(companyDTO);
        company.setShopAdmin(shopAdmin);
        company.getShopAdmin().setCompany(company);
        shopAdmin.setCompany(company);

        Company savedCompany = companyRepository.save(company);

        logger.info("Saved Company with id={} and name={}", company.getId(), company.getName());

        return companyMapper.toDto(savedCompany);
    }


    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO companyDTO, String name) {

        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> {
                    throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                            "company_not_found",
                            "Company with name=" + name + " doesn't exist!");
                });

        Company companyToSave = companyMapper.toEntity(companyDTO);
        companyToSave.setId(company.getId());

        return companyMapper.toDto(companyRepository.save(companyToSave));

    }


    @Override
    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Company with id={} not found!", id);
                            return new ResourceNotFoundException("Company with id=" + id + " not found!");
                        }
                );

        return companyMapper.toDto(company);
    }


    @Override
    public CompanyDTO findByName(String name) {
        logger.info("search by company name: " + name);

        Company company = companyRepository.findByName(name).orElseThrow(() -> {
            logger.error("Company with name={} doesn't exist!", name);
            throw new ResourceNotFoundException("Company with name=" + name + " does not exist!");
        });

        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDTO findByShopAdminId(Long adminId) {
        logger.info("search by company admin id : " + adminId);

        Company company = companyRepository.findByShopAdminId(adminId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Company with shop admin id =" + adminId + " does not exist!");
        });

        return companyMapper.toDto(company);
    }


    private boolean isCompanyExistByName(String name) {
        return companyRepository.findByName(name).isPresent();
    }
}
