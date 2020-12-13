package by.bsuir.service.notification.scheduling;

import by.bsuir.dto.model.notification.OrderNotificationDTO;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.user.ShopAdmin;
import by.bsuir.repository.api.ShopRepository;
import by.bsuir.repository.api.user.ShopAdminRepository;
import by.bsuir.service.notification.api.OrderNotificationService;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.mail.EmailService;
import by.bsuir.service.report.service.api.OrderNotificationReportService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public final class OrderCheckScheduling {

    private final OrderNotificationService orderNotificationService;
    private final OrderNotificationReportService orderNotificationReportService;
    private final EmailService emailService;
    private final ShopAdminRepository shopAdminRepository;
    private final ShopRepository shopRepository;


    @Scheduled(cron = "0 0/15 * * * ?")//дубасит каждый 15 минут
    public void reportCurrentTime() {

        final List<OrderNotificationDTO> allShopsNotifications = orderNotificationService.findAll();

        final Map<Long, List<OrderNotificationDTO>> shopsOrdersMap = new HashMap<>();

        //идет групировка заказов по магазам
        //сначала все магазы сетаем в мапу и к ним пусто лист
        for (final OrderNotificationDTO orderNotification : allShopsNotifications) {
            shopsOrdersMap.put(orderNotification.getShop().getId(), new ArrayList<>());
        }

        //потом получаем магаз по иду и туда в лист доабвляем
        for (final OrderNotificationDTO orderNotification : allShopsNotifications) {
            shopsOrdersMap.get(orderNotification.getShop().getId()).add(orderNotification);
        }

        shopsOrdersMap.forEach((shopId, notifications) -> {
            final Report report =
                    orderNotificationReportService.getShopNewOrdersReport(notifications);
            ShopAdmin admin = null;

            if (shopRepository.existsById(shopId)) {
                Shop shop = shopRepository.getOne(shopId);
                String email = shop.getCompany().getShopAdmin().getEmail();
                admin = shopAdminRepository.getByEmail(email);
            }

            if (Objects.nonNull(admin)) {
                final String email = admin.getEmail();
                emailService.sendReportOnMail(email, report);
            }
        });
    }


}
