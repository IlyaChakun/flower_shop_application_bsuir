package by.bsuir.service.report.service.impl;

import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.service.api.OrderReportService;
import com.lowagie.text.pdf.PdfPCell;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderReportServiceImpl implements OrderReportService {

    private final PdfUtils pdfUtils = new PdfUtils();

    @Override
    public Report getClientOrderReport(OrderDTO orderDTO) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";
        final String email = orderDTO.getClient().getEmail();

        final ClientDTO clientDTO = orderDTO.getClient();
        Report report = new Report();

        report.setFileName(clientDTO.getName() + "_order_report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Детали заявки на перевозку");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(15f, 15f, 15f, 15f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Детали заказа на доставку"));

        List<PdfPCell> driversTableHeaders = getPresentationTableHeaders();
        report.setTableHeaders(driversTableHeaders);

        List<PdfPCell> driversTableCells = getOrderBody(orderDTO);
        report.setTableCells(driversTableCells);

        pdfUtils.createMetadata(report, "Детали вашего заказа",
                clientDTO.getName(), clientDTO.getName(), "newOrderInfo", "Детали заказа на доставку");

        return report;
    }

    private List<PdfPCell> getPresentationTableHeaders() {
        List<PdfPCell> tableHeaders = new ArrayList<>();
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о клиенте"));
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о грузе"));
        tableHeaders.add(pdfUtils.getHeaderCell("Маршрут перевозки"));
        tableHeaders.add(pdfUtils.getHeaderCell("Перевозчик"));
        return tableHeaders;
    }

    private List<PdfPCell> getOrderBody(OrderDTO orderDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();


        final String clientFio = orderDTO.getClient().getName() + "\n";

//        final String cargoInformation =
//                "Тип груза: " + orderDTO.getCargoSpecification().getCargoType() +
////                        "\n Высота: " + orderDTO.getCargoSpecification().getCargoHeight() +
////                        "\n Длина: " + orderDTO.getCargoSpecification().getCargoLength() +
////                        "\n Длина:" + orderDTO.getCargoSpecification().getCargoWidth() +
//                        "\n Вес: " + orderDTO.getCargoSpecification().getCargoWeight();
//
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

        tableCells.add(pdfUtils.getTableCell(clientFio));
//        tableCells.add(pdfUtils.getTableCell(cargoInformation));
//        tableCells.add(pdfUtils.getTableCell(routeInfo.toString()));
//        tableCells.add(pdfUtils.getTableCell(carrierInfo.toString()));


        return tableCells;
    }

}
