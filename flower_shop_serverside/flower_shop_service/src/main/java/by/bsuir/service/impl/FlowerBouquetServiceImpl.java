package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.FlowerBouquetMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.entity.product.bouqet.FlowerBouquet;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.FlowerBouquetRepository;
import by.bsuir.service.api.FlowerBouquetService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class FlowerBouquetServiceImpl implements FlowerBouquetService {


    private static final Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

    private final FlowerBouquetRepository flowerBouquetRepository;
    private final FlowerBouquetMapperDTO flowerBouquetMapperDTO;


    @Override
    public PageWrapper<FlowerBouquetDTO> findAll(int page, int size, SearchAndSortParamDto searchAndSortParamDto) {
        Pageable pageable = getPageable(page, size);

//        Specification<Flower> specification = getSpecification(searchAndSortParamDto);
//        Page<Flower> flowers = flowerRepository.findAll(specification, pageable);
        Page<FlowerBouquet> flowerBouquets = flowerBouquetRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        flowerBouquetMapperDTO.toDtoList(flowerBouquets.toList()),
                        flowerBouquets.getTotalPages(),
                        flowerBouquets.getTotalElements());
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    @Override
    public void delete(Long id) {
        FlowerBouquet flowerBouquet = getFlowerBouquetByIdOrThrowException(id);

        flowerBouquetRepository.delete(flowerBouquet);
    }

    @Override
    public FlowerBouquetDTO findById(Long id) {
        FlowerBouquet flowerBouquet = getFlowerBouquetByIdOrThrowException(id);

        return flowerBouquetMapperDTO.toDto(flowerBouquet);
    }

    private FlowerBouquet getFlowerBouquetByIdOrThrowException(Long id) {
        return flowerBouquetRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower Bouquet with id={} not found!", id);
                            return new ResourceNotFoundException("Flower Bouquet with id=" + id + " not found!");
                        }
                );
    }

    @Override
    @Transactional
    public FlowerBouquetDTO save(FlowerBouquetDTO flowerBouquetDTO) {
        if (isFlowerBouquetExistById(flowerBouquetDTO.getId())) {
            logger.error("Flower Bouquet with id={} exist. Just Update it!", flowerBouquetDTO.getId());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "flower_bouquet_already_exist",
                    "Flower Bouquet with id=" + flowerBouquetDTO.getId() + " exist. Just Update it!");
        }


        FlowerBouquet flowerBouquet = flowerBouquetMapperDTO.toEntity(flowerBouquetDTO);

        FlowerBouquet savedFlowerBouquet = flowerBouquetRepository.save(flowerBouquet);

        logger.info("Saved Flower with id={} and bouquet type={}", flowerBouquet.getId(), flowerBouquet.getBouquetType());

        return flowerBouquetMapperDTO.toDto(savedFlowerBouquet);
    }

    @Override
    @Transactional
    public FlowerBouquetDTO update(FlowerBouquetDTO flowerBouquetDTO) {
        if (!isFlowerBouquetExistById(flowerBouquetDTO.getId())) {
            logger.error("Flower Bouquet with id={} doesn't exist!", flowerBouquetDTO.getId());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                    "flower_bouquet_not_found",
                    "Flower Bouquet with id=" + flowerBouquetDTO.getId() + " doesn't exist!");
        }

        FlowerBouquet flowerBouquet = flowerBouquetMapperDTO.toEntity(flowerBouquetDTO);

        return flowerBouquetMapperDTO.toDto(flowerBouquetRepository.save(flowerBouquet));
    }


    private boolean isFlowerBouquetExistById(Long id) {
        return flowerBouquetRepository.findById(id).isPresent();
    }

}