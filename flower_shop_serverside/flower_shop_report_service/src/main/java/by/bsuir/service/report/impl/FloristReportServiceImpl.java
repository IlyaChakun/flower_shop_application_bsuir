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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
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
    public Report getFloristMonthSalaryReport(Long id, Integer monthNumber) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        FloristDTO floristDto = getFloristDtoById(id);

        Report report = new Report();

        report.setFileName(floristDto.getUser().getName() + "_florist monthly salary report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по зарплате флориста за месяц");

        pdfUtils.setTableSettings(report,
                5,
                Arrays.asList(2f, 12f, 8f, 8f, 8f),
                6f);

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
    public Report getFloristAnnualSalaryReport(Long floristId) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        FloristDTO floristDto = getFloristDtoById(floristId);

        Report report = new Report();

        report.setFileName(floristDto.getUser().getName() + "_florist annual salary report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по зарплате флориста за год");

        pdfUtils.setTableSettings(report,
                5,
                Arrays.asList(2f, 12f, 8f, 8f, 8f),
                6f);

        report.setParagraph(pdfUtils
                .getParagraph(
                        "Сводный отчет по зарплате флориста за год " +
                                floristDto.getUser().getName()
                ));

        List<PdfPCell> floristTableHeaders = getFloristTableHeaders();
        report.setTableHeaders(floristTableHeaders);

        List<PdfPCell> floristTableCells = getFloristAnnualSalaryTableCells(floristDto);
        report.setTableCells(floristTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по зарплате флориста за год",
                floristDto.getUser().getName(), floristDto.getUser().getName(), "floristYearSalaryCard",
                "Сводный отчет по зарплате флориста за год");

        return report;
    }

    @Override
    public Report getFloristOrdersReport(Long floristId) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        FloristDTO floristDto = getFloristDtoById(floristId);

        Report report = new Report();

        report.setFileName(floristDto.getUser().getName() + "_florist orders report");
        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по заказам флориста за месяц");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(2f, 5f, 12f, 12f),
                6f);

        report.setParagraph(pdfUtils
                .getParagraph(
                        "Сводный отчет по заказам флориста за месяц " +
                                floristDto.getUser().getName()
                ));

        List<PdfPCell> floristTableHeaders = getFloristOrdersTableHeaders();
        report.setTableHeaders(floristTableHeaders);

        List<PdfPCell> floristTableCells = getFloristOrdersTableCells(floristDto);
        report.setTableCells(floristTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по заказам флориста за месяц",
                floristDto.getUser().getName(), floristDto.getUser().getName(), "floristYearSalaryCard",
                "Сводный отчет по заказам флориста за месяц");

        return report;
    }

    private List<PdfPCell> getFloristTableHeaders() {
        List<PdfPCell> floristTableHeaders = new ArrayList<>();
        floristTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Общая информация"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Оклад"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Бонус"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Итого"));
        return floristTableHeaders;
    }

    private List<PdfPCell> getFloristOrdersTableHeaders() {
        List<PdfPCell> floristTableHeaders = new ArrayList<>();
        floristTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("№ заказа"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Состав букета"));
        floristTableHeaders.add(pdfUtils.getHeaderCell("Комментарий"));
        return floristTableHeaders;
    }

    private List<PdfPCell> getFloristOrdersTableCells(FloristDTO floristDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();

        LocalDateTime startDate = LocalDateTime.now().withDayOfMonth(1);
        LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);

        List<Order> floristOrders = orderRepository
                .findAllByOrderFloristInfoFloristIdAndOrderFloristInfoFloristCompletionTimeIsBetween(
                        floristDTO.getId(),
                        startDate,
                        endDate);

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

                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
                tableCells.add(pdfUtils.getTableCell(orderNum));
                tableCells.add(pdfUtils.getTableCell(products));
                tableCells.add(pdfUtils.getTableCell(orderComment));
            }
        });

        return tableCells;
    }

    private List<PdfPCell> getFloristMonthlySalaryTableCells(FloristDTO floristDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        String aboutFlorist = getInfoAboutFlorist(floristDTO);

        int monthNumber = LocalDate.now().getMonthValue();
        double salary = floristDTO.getSalary();
        double bonus = getBonusForMonth(floristDTO, monthNumber);

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(aboutFlorist));
        tableCells.add(pdfUtils.getTableCell("За " + monthNumber + " месяц: " + salary + " руб"));
        tableCells.add(pdfUtils.getTableCell("За " + monthNumber + " месяц: " + bonus + " руб"));
        tableCells.add(pdfUtils.getTableCell(Double.toString(salary + bonus)));

        return tableCells;
    }

    private List<PdfPCell> getFloristAnnualSalaryTableCells(FloristDTO floristDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        String aboutFlorist = getInfoAboutFlorist(floristDTO);

        int monthNumber = LocalDate.now().getMonthValue();
        StringBuilder salaryInfo = new StringBuilder();
        StringBuilder bonusInfo = new StringBuilder();
        double totalSalary = 0.0;
        double totalBonus = 0.0;

        for (int month = 1; month <= monthNumber; month++) {
            double monthlySalary = floristDTO.getSalary();
            salaryInfo.append("За ")
                    .append(month)
                    .append(" месяц: ")
                    .append(monthlySalary)
                    .append(" руб\n");
            totalSalary += monthlySalary;

            double monthlyBonus = getBonusForMonth(floristDTO, month);
            bonusInfo.append("Бонус за ")
                    .append(month)
                    .append(" месяц: ")
                    .append(monthlyBonus)
                    .append(" руб\n");
            totalBonus += monthlyBonus;
        }

        salaryInfo
                .append("ИТОГО: ")
                .append(totalSalary)
                .append(" руб.");

        bonusInfo
                .append("ИТОГО: ")
                .append(totalBonus)
                .append(" руб.");

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(aboutFlorist));
        tableCells.add(pdfUtils.getTableCell(salaryInfo.toString()));
        tableCells.add(pdfUtils.getTableCell(bonusInfo.toString()));
        tableCells.add(pdfUtils.getTableCell(Double.toString(totalSalary + totalBonus)));

        return tableCells;
    }

    private FloristDTO getFloristDtoById(Long id) {
        return floristMapper.toDto(floristRepository.getOneByUserId(id));
    }

    private double getBonusForMonth(FloristDTO florist, int monthNumber) {
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(), monthNumber, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);

        List<Order> floristOrders = orderRepository
                .findAllByOrderFloristInfoFloristIdAndOrderFloristInfoFloristCompletionTimeIsBetween(
                        florist.getId(),
                        startDate,
                        endDate);

        double bonus = 0.0;
        if (floristOrders.size() > 10) {
            bonus = florist.getSalary() * 1.5;
        }

        return bonus;
    }

    private String getInfoAboutFlorist(FloristDTO florist) {
        return florist.getUser().getName() +
                ", \n Работает с: " + florist.getDateOfCreation().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) +
                ", \n Кол-во заказов: " + florist.getFloristStatistic().getCompletedOrdersCount();
    }
}
