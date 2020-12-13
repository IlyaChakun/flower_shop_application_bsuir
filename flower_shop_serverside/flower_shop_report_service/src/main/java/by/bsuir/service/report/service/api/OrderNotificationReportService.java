package by.bsuir.service.report.service.api;

import by.bsuir.dto.model.notification.OrderNotificationDTO;
import by.bsuir.service.report.dto.Report;

import java.util.List;

public interface OrderNotificationReportService {

    Report getShopNewOrdersReport(List<OrderNotificationDTO> orderNotificationDTO);

}
