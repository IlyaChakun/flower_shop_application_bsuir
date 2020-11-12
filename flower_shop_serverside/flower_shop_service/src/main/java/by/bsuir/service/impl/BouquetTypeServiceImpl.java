package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.BouquetTypeMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.entity.product.bouqet.BouquetType;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.BouquetTypeRepository;
import by.bsuir.service.api.BouquetTypeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BouquetTypeServiceImpl implements BouquetTypeService {

    private static final Logger logger = LoggerFactory.getLogger(BouquetTypeServiceImpl.class);

    private final BouquetTypeRepository bouquetTypeRepository;
    private final BouquetTypeMapperDTO bouquetTypeMapperDTO;


    @Override
    public PageWrapper<BouquetTypeDTO> findAll(int page, int size) {
        Pageable pageable = getPageable(page, size);

        Page<BouquetType> bouquetTypes = bouquetTypeRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        bouquetTypeMapperDTO.toDtoList(bouquetTypes.toList()),
                        bouquetTypes.getTotalPages(),
                        bouquetTypes.getTotalElements());
    }

    @Override
    public void delete(Long id) {
        BouquetType bouquetType = getBouquetTypeByIdOrThrowException(id);

        bouquetTypeRepository.delete(bouquetType);
    }

    @Override
    public BouquetTypeDTO findById(Long id) {
        BouquetType bouquetType = getBouquetTypeByIdOrThrowException(id);

        return bouquetTypeMapperDTO.toDto(bouquetType);
    }

    @Override//TODO где транзакции??
    public BouquetTypeDTO save(BouquetTypeDTO bouquetTypeDTO) {
        if (isFlowerBouquetExistById(bouquetTypeDTO.getId())) {
            logger.error("Bouquet Type with id={} exist. Just Update it!", bouquetTypeDTO.getId());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "bouquet_type_already_exist",
                    "Bouquet Type with id=" + bouquetTypeDTO.getId() + " exist. Just Update it!");
        }


        BouquetType bouquetType = bouquetTypeMapperDTO.toEntity(bouquetTypeDTO);

        BouquetType savedBouquetType = bouquetTypeRepository.save(bouquetType);

        logger.info("Saved Bouquet with id={} and type={}", bouquetType.getId(), bouquetType.getBouquetType());

        return bouquetTypeMapperDTO.toDto(savedBouquetType);
    }

    @Override
    public BouquetTypeDTO update(BouquetTypeDTO bouquetTypeDTO) {
        if (!isFlowerBouquetExistById(bouquetTypeDTO.getId())) {
            logger.error("Flower Bouquet with id={} doesn't exist!", bouquetTypeDTO.getId());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                    "flower_bouquet_not_found",
                    "Flower Bouquet with id=" + bouquetTypeDTO.getId() + " doesn't exist!");
        }

        BouquetType bouquetType = bouquetTypeMapperDTO.toEntity(bouquetTypeDTO);

        return bouquetTypeMapperDTO.toDto(bouquetTypeRepository.save(bouquetType));
    }


    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }


    private BouquetType getBouquetTypeByIdOrThrowException(Long id) {
        return bouquetTypeRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Bouquet Type with id={} not found!", id);
                            return new ResourceNotFoundException("Bouquet Type with id=" + id + " not found!");
                        }
                );
    }


    private boolean isFlowerBouquetExistById(Long id) {
        return bouquetTypeRepository.findById(id).isPresent();
    }
}
