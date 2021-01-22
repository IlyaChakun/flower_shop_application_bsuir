package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.ProductMapperDTO;
import by.bsuir.dto.model.AbstractSearchAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.entity.product.Product;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final CommonServiceHelper commonServiceHelper;
    private final ProductRepository productRepository;
    private final ProductMapperDTO productMapper;

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        if (Objects.isNull(productDTO.getShopId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "absent_shop_error",
                    "Shop id must be specified!");
        }

        this.resolveShopOrThrowException(productDTO.getShopId());

        Product product = productMapper.toEntity(productDTO);
        product.setProducer(commonServiceHelper.resolveCountry(productDTO.getProducer()));

        Product savedProduct = productRepository.save(product);

       // logger.info("Saved product with id={} and type={}", savedProduct.getId(), savedProduct.getProductType().getType());

        return productMapper.toDto(savedProduct);
    }


    private void resolveShopOrThrowException(Long shopId) {
//        shopRepository.findById(flowerProductDTO.getShop().getId())
//                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + flowerProductDTO.getShop().getId() + " not found!"));
    }


    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        // product.getShop().getShopProducts().remove(flower);//TODO

        productRepository.delete(product);
    }


    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        return productMapper.toDto(product);
    }


    @Override
    public PageWrapper<ProductDTO> findAll(int page, int size, AbstractSearchAndSortParamsDto searchParams) {
        Pageable pageable = commonServiceHelper.getPageable(page, size);

//        Specification<Flower> specification = getSpecification(searchAndSortParamDto);
//        Page<Flower> flowers = flowerRepository.findAll(specification, pageable);
        Page<Product> products = productRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        productMapper.toDtoList(products.toList()),
                        products.getTotalPages(),
                        products.getTotalElements());
    }
}
