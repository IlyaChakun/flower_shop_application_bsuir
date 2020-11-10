package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.FlowerMapperDTO;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.FlowerRepository;
import by.bsuir.service.api.FlowerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private static final Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

    private final FlowerRepository flowerRepository;
    private final FlowerMapperDTO flowerMapper;

    @Override
    public FlowerDTO findById(Long id) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        return flowerMapper.toDto(flower);
    }

    @Override
    public FlowerDTO save(FlowerDTO flowerDTO) {
        if (isFlowerExistById(flowerDTO.getId())) {
            logger.error("Flower with id={} exist. Just Update it!", flowerDTO.getId());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "flower_already_exist",
                    "Flower with id=" + flowerDTO.getId() + " exist. Just Update it!");
        }


        Flower flower = flowerMapper.toEntity(flowerDTO);

        Flower savedFlower = flowerRepository.save(flower);

        logger.info("Saved Flower with id={} and type={}", flower.getId(), flower.getFlowerType());

        return flowerMapper.toDto(savedFlower);
    }

    @Override
    public FlowerDTO update(FlowerDTO flowerDTO) {
        if (!isFlowerExistById(flowerDTO.getId())) {
            logger.error("Flower with id={} doesn't exist!", flowerDTO.getId());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                    "flower_not_found",
                    "Flower with id=" + flowerDTO.getId() + " doesn't exist!");
        }

        Flower flowerToSave = flowerMapper.toEntity(flowerDTO);

        return flowerMapper.toDto(flowerRepository.save(flowerToSave));
    }

    @Override
    public void delete(Long id) {

        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        flowerRepository.delete(flower);
    }

    private boolean isFlowerExistById(Long id) {
        return flowerRepository.findById(id).isPresent();
    }


}
