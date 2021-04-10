package by.bsuir.service.report.impl;

import by.bsuir.dto.mapper.florist.FloristMapperDTO;
import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderProduct;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.product.Product;
import by.bsuir.repository.api.florist.FloristRepository;
import by.bsuir.repository.api.order.OrderRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.service.report.api.FloristReportService;
import by.bsuir.service.report.dto.Report;
import com.lowagie.text.pdf.PdfPCell;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FloristReportServiceImpl implements FloristReportService {
    private final PdfUtils pdfUtils = new PdfUtils();
    private final FloristRepository floristRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final FloristMapperDTO floristMapper;

    @Override
    public Report getFloristMonthSalaryReport(String email, Integer monthNumber) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        FloristDTO floristDto = getFloristDtoByEmail(email);

        Report report = new Report();

        report.setFileName(floristDto.getUser().getName() + "_florist monthly salary report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по зарплате флориста за месяц");

        pdfUtils.setTableSettings(report,
                6,
                Arrays.asList(2f, 4f, 8f, 12f, 4f, 4f),
                10f);

        report.setParagraph(pdfUtils
                .getParagraph(
                        "Сводный отчет по зарплате флориста за месяц " +
                                floristDto.getUser().getName()
                ));

        List<PdfPCell> floristTableHeaders = getFloristTableHeaders();
        report.setTableHeaders(floristTableHeaders);

        List<PdfPCell> floristTableCells = getFloristMonthlySalaryTableCells(floristDto);
        report.setTableCells(floristTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по зарплате флориста за месяц",
                floristDto.getUser().getName(), floristDto.getUser().getName(), "floristMonthlySalaryCard",
                "Сводный отчет по зарплате флориста за месяц");

        return report;
    }

    @Override
    public Report getFloristYearSalaryReport(String email) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        FloristDTO floristDto = getFloristDtoByEmail(email);

        Report report = new Report();

        report.setFileName(floristDto.getUser().getName() + "_florist year salary report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по зарплате флориста за год");

        pdfUtils.setTableSettings(report,
                6,
                Arrays.asList(2f, 4f, 8f, 12f, 4f, 4f),
                10f);

        report.setParagraph(pdfUtils
                .getParagraph(
                        "Сводный отчет по зарплате флориста за год " +
                                floristDto.getUser().getName()
                ));

        List<PdfPCell> floristTableHeaders = getFloristTableHeaders();
        report.setTableHeaders(floristTableHeaders);

        List<PdfPCell> floristTableCells = getFloristYearSalaryTableCells(floristDto);
        report.setTableCells(floristTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по зарплате флориста за год",
                floristDto.getUser().getName(), floristDto.getUser().getName(), "floristYearSalaryCard",
                "Сводный отчет по зарплате флориста за год");

        return report;
    }

    @Override
    public Report getFloristOrdersReport(String email) {
        return null;
    }

    private FloristDTO getFloristDtoByEmail(String email) {
        return floristMapper.toDto(floristRepository.getByUserEmail(email));
    }

    private List<PdfPCell> getFloristTableHeaders() {
        List<PdfPCell> floristTableHeaders = new ArrayList<>();
        floristTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("№ заказа"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Состав букета"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Комментарий"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Ставка оплаты ($/час)"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Выплата"));
        return floristTableHeaders;
    }

    private List<PdfPCell> getFloristMonthlySalaryTableCells(FloristDTO floristDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        //driver info

        final Double paymentPerHour = 10.5;

        List<Order> floristOrders = orderRepository
                .findAllByOrderFloristInfoFloristId(floristDTO.getId());

        AtomicReference<Double> sum = new AtomicReference<>(0.0);

        floristOrders.forEach(order -> {

            if (Objects.nonNull(order.getOrderFloristInfo()) &&
                    order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                String orderNum = order.getUniqueId();

                List<OrderProduct> orderProducts = order.getOrderProducts();
                String products = orderProducts
                        .stream()
                        .map(orderProduct -> {
                            Product product = productRepository.getOne(orderProduct.getProductId());
                            return product.getTitle() + " " + orderProduct.getQuantity() + " шт";
                        })
                        .collect(Collectors.joining(" ,"));

                String orderComment = order.getOrderFloristInfo().getFloristComment();
                LocalDateTime orderStartTime = order.getOrderFloristInfo().getFloristAppointmentTime();
                LocalDateTime orderEndTime = order.getOrderFloristInfo().getFloristCompletionTime();
                Double timeToCompleteOrder = LocalDateTime
                        .ofEpochSecond(orderEndTime.toEpochSecond(ZoneOffset.UTC) - orderStartTime.toEpochSecond(ZoneOffset.UTC), 0,
                                ZoneOffset.UTC).getMinute() / 60.0;

                double payment = paymentPerHour * timeToCompleteOrder;
                sum.updateAndGet(v -> v + payment);

                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
                tableCells.add(pdfUtils.getTableCell(orderNum));
                tableCells.add(pdfUtils.getTableCell(products));
                tableCells.add(pdfUtils.getTableCell(order.getOrderFloristInfo().getFloristComment()));
                tableCells.add(pdfUtils.getTableCell(paymentPerHour.toString()));
                tableCells.add(pdfUtils.getTableCell(Double.toString(payment)));
            }
        });

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(""));
        tableCells.add(pdfUtils.getTableCell(""));
        tableCells.add(pdfUtils.getTableCell("Оклад: " + floristDTO.getSalary()));
        tableCells.add(pdfUtils.getTableCell("Итого:"));
        tableCells.add(pdfUtils.getTableCell(Double.toString(floristDTO.getSalary() + sum.get())));

        return tableCells;
    }

    private List<PdfPCell> getFloristYearSalaryTableCells(FloristDTO floristDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        //driver info

        final Double paymentPerHour = 10.5;

        List<Order> floristOrders = orderRepository
                .findAllByOrderFloristInfoFloristId(floristDTO.getId());

        AtomicReference<Double> sum = new AtomicReference<>(0.0);

        floristOrders.forEach(order -> {

            if (Objects.nonNull(order.getOrderFloristInfo()) &&
                    order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                String orderNum = order.getUniqueId();

                List<OrderProduct> orderProducts = order.getOrderProducts();
                String products = orderProducts
                        .stream()
                        .map(orderProduct -> {
                            Product product = productRepository.getOne(orderProduct.getProductId());
                            return product.getTitle() + " " + orderProduct.getQuantity() + " шт";
                        })
                        .collect(Collectors.joining(" ,"));

                String orderComment = order.getOrderFloristInfo().getFloristComment();
                LocalDateTime orderStartTime = order.getOrderFloristInfo().getFloristAppointmentTime();
                LocalDateTime orderEndTime = order.getOrderFloristInfo().getFloristCompletionTime();
                Double timeToCompleteOrder = LocalDateTime
                        .ofEpochSecond(orderEndTime.toEpochSecond(ZoneOffset.UTC) - orderStartTime.toEpochSecond(ZoneOffset.UTC), 0,
                                ZoneOffset.UTC).getMinute() / 60.0;

                double payment = paymentPerHour * timeToCompleteOrder;
                sum.updateAndGet(v -> v + payment);

                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
                tableCells.add(pdfUtils.getTableCell(orderNum));
                tableCells.add(pdfUtils.getTableCell(products));
                tableCells.add(pdfUtils.getTableCell(order.getOrderFloristInfo().getFloristComment()));
                tableCells.add(pdfUtils.getTableCell(paymentPerHour.toString()));
                tableCells.add(pdfUtils.getTableCell(Double.toString(payment)));
            }
        });

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(""));
        tableCells.add(pdfUtils.getTableCell(""));
        tableCells.add(pdfUtils.getTableCell("Оклад: " + floristDTO.getSalary()));
        tableCells.add(pdfUtils.getTableCell("Итого:"));
        tableCells.add(pdfUtils.getTableCell(Double.toString(floristDTO.getSalary() + sum.get())));

        return tableCells;
    }
}
