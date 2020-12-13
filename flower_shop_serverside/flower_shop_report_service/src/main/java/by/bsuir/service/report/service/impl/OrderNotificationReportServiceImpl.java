package by.bsuir.service.report.service.impl;

import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.dto.model.notification.OrderNotificationDTO;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.service.api.OrderNotificationReportService;
import com.lowagie.text.pdf.PdfPCell;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class OrderNotificationReportServiceImpl implements OrderNotificationReportService {

    private final PdfUtils pdfUtils = new PdfUtils();

    @Override
    public Report getShopNewOrdersReport(List<OrderNotificationDTO> companyNotifications) {


        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        Report report = new Report();


        final CompanyDTO companyDTO = companyNotifications.get(0).getCompany();//TODO


        report.setFileName(companyDTO.getName() + "_" + "_orders_notification_report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Ваши новые заявки");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(15f, 15f, 15f, 15f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Детали заявки на перевозку"));

        List<PdfPCell> driversTableHeaders = getPresentationTableHeaders();
        report.setTableHeaders(driversTableHeaders);

        List<PdfPCell> driversTableCells = getOrderBody(companyNotifications);
        report.setTableCells(driversTableCells);

        pdfUtils.createMetadata(report, "Детали вашей заявки",
                companyDTO.getName(), companyDTO.getName(), "newOrderInfo", "Детали заявки на перевозку");


        return report;
    }

    private List<PdfPCell> getPresentationTableHeaders() {
        List<PdfPCell> tableHeaders = new ArrayList<>();
        tableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о клиенте"));
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о товаре"));
        tableHeaders.add(pdfUtils.getHeaderCell("Маршрут перевозки"));
        tableHeaders.add(pdfUtils.getHeaderCell("Перевозчик"));
        return tableHeaders;
    }

    private List<PdfPCell> getOrderBody(List<OrderNotificationDTO> companyNotifications) {

        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        //truck info
        companyNotifications.forEach(notification -> {
            addTableCells(count, notification.getOrder(), tableCells);
        });
        return tableCells;
    }

    private void addTableCells(AtomicInteger count, OrderDTO orderDTO, List<PdfPCell> tableCells) {
        final String clientFio = orderDTO.getClient().getName() +
                "\n";

        final String cargoInformation ="";
//                "Тип груза: " + orderDTO.getCargoSpecification().getCargoType() +
//                        "\n Вес: " + orderDTO.getCargoSpecification().getCargoWeight();

//        StringBuilder routeInfo = new StringBuilder();
//        routeInfo.append("Маршрут: \n Откуда: ")
//                .append(orderDTO.getRoute().getRouteOrigin())
//                .append("\n Куда: ")
//                .append(orderDTO.getRoute().getRouteDestination())
//                .append("\n Дата отправки: ")
//                .append(orderDTO.getRoute().getDayOfArrival())
//                .append("\n Дата прибытия: ")
//                .append(orderDTO.getRoute().getDayOfDispatch())
//                .append("\n Детали маршрута: ");
//        orderDTO.getRoute().getSegments().forEach(segment -> {
//            routeInfo
//                    .append("\n Номер сегмента: ")
//                    .append(segment.getSegmentNumber())
//                    .append("\nТочка отправки: ")
//                    .append(segment.getOrigin())
//                    .append("\nТочка прибытия: ")
//                    .append(segment.getDestination())
//                    .append("\nДистанция: ")
//                    .append(segment.getDistance())
//                    .append("\nВремя в пути: ")
//                    .append(segment.getTime());
//        });
//
//        StringBuilder carrierInfo = new StringBuilder();
//        carrierInfo
//                .append("Компания: ")
//                .append(orderDTO.getOrderInfo().getCompany().getOrganizationName())
//                .append("\n Дата создания: ")
//                .append(orderDTO.getOrderInfo().getCompany().getOrganizationCreationDate());

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(clientFio));
        tableCells.add(pdfUtils.getTableCell(cargoInformation));
//        tableCells.add(pdfUtils.getTableCell(routeInfo.toString()));
//        tableCells.add(pdfUtils.getTableCell(carrierInfo.toString()));

    }


}
