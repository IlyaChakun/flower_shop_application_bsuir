package by.bsuir.service.notification.impl;

import by.bsuir.dto.mapper.notification.OrderNotificationMapper;
import by.bsuir.dto.model.notification.OrderNotificationDTO;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.ordernotification.OrderNotification;
import by.bsuir.repository.api.OrderNotificationRepository;
import by.bsuir.service.notification.api.OrderNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderNotificationServiceImpl implements OrderNotificationService {

    private final OrderNotificationMapper orderNotificationMapper;
    private final OrderNotificationRepository orderNotificationRepository;

    @Override
    @Transactional
    public List<OrderNotificationDTO> findAll() {
        List<OrderNotification> notifications = orderNotificationRepository.findAll();
        return orderNotificationMapper.toDtoList(notifications);
    }

    @Override
    @Transactional
    public List<OrderNotificationDTO> findAllWithStatusNew() {
        List<OrderNotification> notifications =
                orderNotificationRepository
                        .findAllByOrderOrderStatus(OrderStatus.NEW);//new тк нужны которые тока пришли
        return orderNotificationMapper.toDtoList(notifications);
    }

    @Override
    @Transactional
    public Integer getCompanyNewOrdersCount() {
        return findAllWithStatusNew().size();
    }


}

