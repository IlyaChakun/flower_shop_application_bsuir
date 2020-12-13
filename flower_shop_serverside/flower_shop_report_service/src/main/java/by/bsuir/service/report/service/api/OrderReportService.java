package by.bsuir.service.report.service.api;


import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.report.dto.Report;

public interface OrderReportService {

    Report getClientOrderReport(OrderDTO orderDTO);

}
