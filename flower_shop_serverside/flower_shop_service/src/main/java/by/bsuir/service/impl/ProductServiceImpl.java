package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.ProductMapperDTO;
import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.entity.product.Product;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.common.CountryRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.repository.api.shop.ShopRepository;
import by.bsuir.service.api.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final CommonServiceHelper commonServiceHelper;

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final CountryRepository countryRepository;

    private final ProductMapperDTO productMapper;

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {

        resolveLinkedEntity(productDTO);

        Product product = productMapper.toEntity(productDTO);
        product.setProducerId(productDTO.getProducerId());

        Product savedProduct = productRepository.save(product);

        log.info("Saved product with id={} and category={}", savedProduct.getId(), savedProduct.getCategoryId());

        return productMapper.toDto(savedProduct);
    }


    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        commonServiceHelper.checkIdOrThrowException(productDTO.getId(), "absent_product_id", "For update operation product id must be set!");

        resolveLinkedEntity(productDTO);

        Product prevProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id =" + productDTO.getId() + " not found!"));

        Product newProduct = productMapper.toEntity(productDTO);
        newProduct.setDateOfCreation(prevProduct.getDateOfCreation());
        newProduct.setUniqueId(prevProduct.getUniqueId());

        Product savedProduct = productRepository.save(newProduct);

        return productMapper.toDto(savedProduct);
    }

    private void resolveLinkedEntity(ProductDTO productDTO) {
      //  commonServiceHelper.checkIdOrThrowException(productDTO.getShopId(), "absent_shop_error", "Shop id must be specified!");
        commonServiceHelper.checkIdOrThrowException(productDTO.getProducerId(), "absent_producer_error", "Producer id must be specified!");

       // this.resolveShopOrThrowException(productDTO.getShopId());
        this.resolveProducerOrThrowException(productDTO.getProducerId());
    }

//
//    private void resolveShopOrThrowException(Long shopId) {
//        shopRepository.findById(shopId)
//                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + shopId + " not found!"));
//    }

    private void resolveProducerOrThrowException(Long producerId) {
        countryRepository.findById(producerId)
                .orElseThrow(() -> new ResourceNotFoundException("Producer with id=" + producerId + " not found!"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        productRepository.delete(product);
    }


    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("Product with id={} not found!", id);
                            return new ResourceNotFoundException("Product with id=" + id + " not found!");
                        }
                );

        return productMapper.toDto(product);
    }


    @Override
    public PageWrapper<ProductDTO> findAll(int page, int size, AbstractSearchCriteriaAndSortParamsDto searchParams) {
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
