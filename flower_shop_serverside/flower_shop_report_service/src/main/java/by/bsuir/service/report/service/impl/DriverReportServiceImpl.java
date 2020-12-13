package by.bsuir.service.report.service.impl;


import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.service.api.DriverReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DriverReportServiceImpl implements DriverReportService {

    private final PdfUtils pdfUtils = new PdfUtils();
//    private final DriverRepository driverRepository;
//    private final DriverMapperDTO driverMapper;


    @Override
    public Report getDriverPersonalReport(String email) {

//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        DriverDTO driverDTO = driverMapper.toDto(driverRepository.getByEmail(email));
//
//        Report report = new Report();
//
//        report.setFileName(driverDTO.getLastName() + " " +
//                driverDTO.getFirstName() + " " +
//                driverDTO.getPatronymic() + "_personal report");
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Персональный отчет по проделанной работе");
//
//        pdfUtils.setTableSettings(report,
//                8,
//                Arrays.asList(2f, 4f, 4f, 4f, 4f, 4f, 4f, 4f),
//                10f);
//
//        report.setParagraph(pdfUtils
//                .getParagraph(
//                        "Персональный отчет по проделанной работе " +
//                                driverDTO.getLastName() + " " +
//                                driverDTO.getFirstName() + " " +
//                                driverDTO.getPatronymic()
//                ));
//
//        List<PdfPCell> driverTableHeaders = getDriverTableHeaders();
//        report.setTableHeaders(driverTableHeaders);
//
//        List<PdfPCell> driversTableCells = getDriverTableCells(driverDTO);
//        report.setTableCells(driversTableCells);
//
//        pdfUtils.createMetadata(report, "Персональный отчет по проделанной работе",
//                driverDTO.getLastName(), driverDTO.getLastName(), "driverPersonalCard", "Персональный отчет по проделанной работе");
//
//        return report;
        return null;
    }


//    private List<PdfPCell> getDriverTableCells(DriverDTO driver) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        AtomicInteger count = new AtomicInteger();
//        //driver info
//
//        final Double paymentPerHour = driver.getDriverPayment().getPaymentPerHour();
//
//        driver.getDriverHistory().getDriverOrders().forEach(order -> {
//
//            if (Objects.nonNull(order.getOrderInfo()) &&
//                    Objects.nonNull(order.getOrderInfo().getTransportOrderCosts()) &&
//                    Objects.nonNull(order.getOrderInfo().getTransportOrderCosts().getCompanyTransportationCosts()) &&
//                    !order.getOrderStatus().equals(OrderStatus.DRAFT)) {
//                String orderNum = order.getUniqueId();
//                String truck = order.getOrderInfo().getDriver().getTruck().getTruckType().getType() + ",\n гос.номер: " +
//                        order.getOrderInfo().getDriver().getTruck().getPlateNumber();
//
//                String route = order.getRoute().getRouteOrigin() + " - " + order.getRoute().getRouteDestination();
//                Double routeDistance = order.getRoute().getRouteFullDistance();
//                Double routeHours = Math.ceil(
//                        order.getRoute()
//                                .getSegments()
//                                .stream()
//                                .mapToDouble(RouteSegmentDTO::getRouteTimeInSeconds)
//                                .sum() / 60 / 60);
//                // получаем время пути, суммируя по всем отрезкам
//
//                Double costPerKm = order.getOrderInfo().getTransportOrderCosts().getCompanyTransportationCosts().getTransportationCostPerKm();
//                Double ratePercent = order.getOrderInfo().getTransportOrderCosts().getCompanyTransportationCosts().getDriverPayRatePercent();
//
//                double payment = routeDistance * (costPerKm * ratePercent / 100) + routeHours * paymentPerHour;
//
//                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
//                tableCells.add(pdfUtils.getTableCell(orderNum));
//                tableCells.add(pdfUtils.getTableCell(truck));
//                tableCells.add(pdfUtils.getTableCell(route));
//                tableCells.add(pdfUtils.getTableCell(routeDistance.toString()));
//                tableCells.add(pdfUtils.getTableCell(routeHours.toString()));
//                tableCells.add(pdfUtils.getTableCell(paymentPerHour.toString()));
//                tableCells.add(pdfUtils.getTableCell(Double.toString(payment)));
//            }
//        });
//
//        return tableCells;
//    }
//
//
//    private List<PdfPCell> getDriverTableHeaders() {
//        List<PdfPCell> driversTableHeaders = new ArrayList<>();
//        driversTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("№ заказа"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Автомобиль"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Маршрут"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Расстояние"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Часы в пути"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Ставка оплаты ($/час)"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Выплата"));
//        return driversTableHeaders;
//    }

}
