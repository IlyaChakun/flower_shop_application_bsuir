package by.bsuir.service;

import by.bsuir.dto.mapper.ProductMapperDTO;
import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.ProductDTO;
import by.bsuir.entity.Product;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        resolveLinkedEntity(productDTO);

        Product product = productMapper.toEntity(productDTO);
        //product.setProducerId(productDTO.getProducerId());

        Product savedProduct = productRepository.save(product);

        // logger.info("Saved product with id={} and type={}", savedProduct.getId(), savedProduct.getProductType().getType());

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
        // commonServiceHelper.checkIdOrThrowException(productDTO.getShopId(), "absent_shop_error", "Shop id must be specified!");
        commonServiceHelper.checkIdOrThrowException(productDTO.getProducerId(), "absent_producer_error", "Producer id must be specified!");

        //  this.resolveShopOrThrowException(productDTO.getShopId());
        //this.resolveProducerOrThrowException(productDTO.getProducerId());
    }


    private void resolveShopOrThrowException(Long shopId) {
//        shopRepository.findById(shopId)
//                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + shopId + " not found!"));
    }

    private void resolveProducerOrThrowException(Long producerId) {
//        countryRepository.findById(producerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Producer with id=" + producerId + " not found!"));
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

        productRepository.delete(product);
    }


    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Product with id={} not found!", id);
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
