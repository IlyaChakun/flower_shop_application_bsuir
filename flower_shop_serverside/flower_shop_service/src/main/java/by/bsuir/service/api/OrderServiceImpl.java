package by.bsuir.service.api;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public PageWrapper<OrderDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderDTO findById(Long id) {
        return null;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        return null;
    }
}
