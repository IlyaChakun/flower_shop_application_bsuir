package by.bsuir.service.impl;

import by.bsuir.dto.model.AbstractSearchAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.product.ProductDTO;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDTO findByIdAndClientId(Long orderId, Long userId) {
        return null;
    }

    @Override
    public PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long userId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PageWrapper<ProductDTO> findAll(int page, int size, AbstractSearchAndSortParamsDto searchParams) {
        return null;
    }

    @Override
    public ProductDTO findById(Long id) {
        return null;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        return null;
    }
}
