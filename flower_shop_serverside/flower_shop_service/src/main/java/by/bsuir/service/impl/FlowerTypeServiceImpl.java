package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.FlowerTypeMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.entity.product.flower.FlowerType;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.FlowerTypeRepository;
import by.bsuir.service.api.FlowerTypeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor//TODO не выкупил нафига этот сервис вроде же базарили что эта шляпа изначально в базе а теперь придется писать контроллер и логику чтобы можно было добавиять типу цветов в каждый магазин, напишешь в туду сам для фронта :)
public class FlowerTypeServiceImpl implements FlowerTypeService {


    private static final Logger logger = LoggerFactory.getLogger(FlowerTypeServiceImpl.class);

    private final FlowerTypeRepository flowerTypeRepository;
    private final FlowerTypeMapperDTO flowerTypeMapperDTO;


    @Override
    public PageWrapper<FlowerTypeDTO> findAll(int page, int size) {
        Pageable pageable = getPageable(page, size);

        Page<FlowerType> flowerTypes = flowerTypeRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        flowerTypeMapperDTO.toDtoList(flowerTypes.toList()),
                        flowerTypes.getTotalPages(),
                        flowerTypes.getTotalElements());
    }

    @Override
    public void delete(Long id) {
        FlowerType flowerType = getFlowerTypeByIdOrThrowException(id);

        flowerTypeRepository.delete(flowerType);
    }

    @Override
    public FlowerTypeDTO findById(Long id) {
        FlowerType flowerType = getFlowerTypeByIdOrThrowException(id);

        return flowerTypeMapperDTO.toDto(flowerType);
    }

    @Override
    public FlowerTypeDTO save(FlowerTypeDTO flowerTypeDTO) {
        if (isFlowerBouquetExistById(flowerTypeDTO.getId())) {
            logger.error("Bouquet Type with id={} exist. Just Update it!", flowerTypeDTO.getId());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "bouquet_type_already_exist",
                    "Bouquet Type with id=" + flowerTypeDTO.getId() + " exist. Just Update it!");
        }


        FlowerType flowerType = flowerTypeMapperDTO.toEntity(flowerTypeDTO);

        FlowerType savedFlowerType = flowerTypeRepository.save(flowerType);

        logger.info("Saved Flower Type with id={} and name={}", flowerType.getId(), flowerType.getFlowerType());

        return flowerTypeMapperDTO.toDto(savedFlowerType);
    }

    @Override
    public FlowerTypeDTO update(FlowerTypeDTO flowerTypeDTO) {
        if (!isFlowerBouquetExistById(flowerTypeDTO.getId())) {
            logger.error("Flower Type with id={} doesn't exist!", flowerTypeDTO.getId());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                    "flower_type_not_found",
                    "Flower Type with id=" + flowerTypeDTO.getId() + " doesn't exist!");
        }

        FlowerType flowerType = flowerTypeMapperDTO.toEntity(flowerTypeDTO);

        return flowerTypeMapperDTO.toDto(flowerTypeRepository.save(flowerType));
    }


    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }


    private FlowerType getFlowerTypeByIdOrThrowException(Long id) {
        return flowerTypeRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower Type with id={} not found!", id);
                            return new ResourceNotFoundException("Flower Type with id=" + id + " not found!");
                        }
                );
    }


    private boolean isFlowerBouquetExistById(Long id) {
        return flowerTypeRepository.findById(id).isPresent();
    }
}
