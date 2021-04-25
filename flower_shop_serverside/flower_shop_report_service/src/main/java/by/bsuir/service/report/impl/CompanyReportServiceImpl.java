package by.bsuir.service.report.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.florist.Florist;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.shop.Shop;
import by.bsuir.entity.user.User;
import by.bsuir.repository.api.common.CityRepository;
import by.bsuir.repository.api.company.CompanyRepository;
import by.bsuir.repository.api.florist.FloristRepository;
import by.bsuir.repository.api.order.OrderRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.repository.api.shop.ShopRepository;
import by.bsuir.repository.api.user.UserRepository;
import by.bsuir.service.report.api.CompanyReportService;
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
public class CompanyReportServiceImpl implements CompanyReportService {

    private final PdfUtils pdfUtils = new PdfUtils();
    private final CompanyRepository companyRepository;
    private final FloristRepository floristRepository;
    private final ShopRepository shopRepository;
    private final CityRepository cityRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CompanyMapperDTO companyMapperDTO;

    @Override
    public Report getCompanyPresentationReport() {

        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        CompanyDTO companyDTO = getCompany();

        Report report = new Report();

        report.setFileName("Presentation - " + companyDTO.getName());

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Карточка компании");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(14f, 9f, 5f, 11f),
                6f);

        report.setParagraph(pdfUtils.getParagraph(companyDTO.getName()));

        List<PdfPCell> tableHeaders = getPresentationTableHeaders();
        report.setTableHeaders(tableHeaders);

        List<PdfPCell> tableCells = getPresentationTableCells(companyDTO);
        report.setTableCells(tableCells);

        pdfUtils.createMetadata(report, "Карточка компании",
                companyDTO.getName(), companyDTO.getLicenceNumber(), "card", "Карточка компании");

        return report;

    }

    @Override
    public Report getCompanyMonthlyReport(Integer monthNumber) {

        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        CompanyDTO companyDTO = getCompany();

        Report report = new Report();

        report.setFileName(companyDTO.getName() + "_accounting monthly report for " + LocalDate.now().getMonth().name());

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет компании за " + LocalDate.now().getMonth().name() + " месяц");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(2f, 12f, 10f, 8f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Сводный отчет компании " + companyDTO.getName() + " за месяц."));

        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
        report.setTableHeaders(accountingReportHeaders);

        List<PdfPCell> monthlyAccountingReportTableCells = getMonthAccountingReportTableCells(companyDTO);
        report.setTableCells(monthlyAccountingReportTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет компании за месяц.",
                companyDTO.getName(), companyDTO.getLicenceNumber(), "accountingMonthlyCard", "Сводный отчет компании за месяц.");

        return report;
    }

    @Override
    public Report getCompanyAnnualReport() {

        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        CompanyDTO companyDTO = getCompany();

        Report report = new Report();

        report.setFileName(companyDTO.getName() + "_accounting annual report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный годовой отчет компании");

        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(2f, 12f, 10f, 10f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Сводный годовой отчет компании " + companyDTO.getName() + "."));

        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
        report.setTableHeaders(accountingReportHeaders);

        List<PdfPCell> annualAccountingReportTableCells = getAnnualAccountingReportTableCells(companyDTO);
        report.setTableCells(annualAccountingReportTableCells);

        pdfUtils.createMetadata(report, "Сводный годовой отчет компании.",
                companyDTO.getName(),
                companyDTO.getLicenceNumber(),
                "accountingAnnualCard",
                "Сводный годовой отчет компании."
        );

        return report;
    }

    private List<PdfPCell> getPresentationTableHeaders() {
        List<PdfPCell> tableHeaders = new ArrayList<>();
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о компании"));
        tableHeaders.add(pdfUtils.getHeaderCell("Точки продаж компании"));
        tableHeaders.add(pdfUtils.getHeaderCell("Виды продуктов"));
        tableHeaders.add(pdfUtils.getHeaderCell("Штат сотрудников"));
        return tableHeaders;
    }

    private List<PdfPCell> getPresentationTableCells(CompanyDTO companyDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        User admin = userRepository.getOne(companyDTO.getAdminId());

        final String fio = "Администратор компании: \n" + admin.getName();

        final String contacts = "Основной телефон: " + companyDTO.getContacts().getFirstPhoneNumber() +
                "\nДоп: " + companyDTO.getContacts().getSecondPhoneNumber() +
                "\nEmail: " + companyDTO.getContacts().getEmail();

        final String organizationDate;

        if (Objects.nonNull(companyDTO.getDateOfCreation())) {
            organizationDate = "Дата основания компании:\n"
                    + companyDTO.getDateOfCreation().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        } else {
            organizationDate = "Дата основания компании: не указана";
        }

        final String address = "Адрес:\n" +
                getCityById(companyDTO.getContacts().getCityId()) +
                ", " + companyDTO.getContacts().getAddress();

        List<Florist> florists = floristRepository.findAll();
        int count = florists.size();
        String size = "";
        if (count == 0) {
            size = "Нет работников";
        } else if (count < 5) {
            size = "до 5 работников";
        } else if (count >= 10 && count < 20) {
            size = "от 10 до 20 работников";
        } else if (count >= 20 && count < 50) {
            size = "от 20 до 50 работников";
        } else if (count >= 50 && count < 100) {
            size = "от 50 до 100 работников";
        } else if (count >= 100) {
            size = "свыше 100 работников";
        }

        final String employeesSize = "Численность работников: " + size;

        final String fullData = fio + "\n\n" +
                contacts + "\n\n" +
                organizationDate + "\n\n" +
                address + "\n\n" +
                employeesSize;

        tableCells.add(pdfUtils.getTableCell(fullData));

        String shopsNames = shopRepository.findAll().stream()
                .map(shop -> getCityById(shop.getContacts().getCityId()) + ", " + shop.getContacts().getAddress())
                .collect(Collectors.joining(";\n"));
        tableCells.add(pdfUtils.getTableCell(shopsNames));

        String productTypes = productRepository.findAll().stream()
                .map(product -> product.getTitle())
                .collect(Collectors.joining(";\n"));
        tableCells.add(pdfUtils.getTableCell(productTypes));

        String floristNames = florists.stream()
                .map(florist -> florist.getUser().getName() + ", тел.:" + florist.getUser().getPhoneNumber())
                .collect(Collectors.joining(",\n"));
        tableCells.add(pdfUtils.getTableCell(floristNames));

        return tableCells;
    }

    private List<PdfPCell> getAccountingReportHeaders() {
        List<PdfPCell> monthlyAccountingReportHeaders = new ArrayList<>();
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Сводная информация"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Зарплата работников"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Отчет по продажам"));
        return monthlyAccountingReportHeaders;
    }

    private List<PdfPCell> getAnnualAccountingReportTableCells(CompanyDTO companyDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();
        String aboutCompany = getInfoAboutCompany(companyDTO);

        int monthNumber = LocalDate.now().getMonthValue();
        StringBuilder salaryInfo = new StringBuilder();
        StringBuilder ordersProfitInfo = new StringBuilder();
        double totalSalary = 0.0;
        double totalProfit = 0.0;

        for (int month = 1; month <= monthNumber; month++) {
            double monthlySalary = getSalaryForMonth(month);
            salaryInfo.append("За ")
                    .append(month)
                    .append(" месяц: ")
                    .append(monthlySalary)
                    .append(" руб\n");
            totalSalary += monthlySalary;

            double monthlyProfit = getOrdersForMonth(month).stream()
                    .map(order -> order.getOrderPriceInfo().getTotalAmount())
                    .mapToDouble(Double::doubleValue)
                    .sum();
            ordersProfitInfo.append("Выручка за ")
                    .append(month)
                    .append(" месяц: ")
                    .append(monthlyProfit)
                    .append(" руб\n");
            totalProfit += monthlyProfit;
        }

        salaryInfo
                .append("ИТОГО: ")
                .append(totalSalary)
                .append(" руб.");

        ordersProfitInfo
                .append("ИТОГО: ")
                .append(totalProfit)
                .append(" руб.");

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(aboutCompany));
        tableCells.add(pdfUtils.getTableCell(salaryInfo.toString()));
        tableCells.add(pdfUtils.getTableCell(ordersProfitInfo.toString()));

        return tableCells;
    }

    private List<PdfPCell> getMonthAccountingReportTableCells(CompanyDTO companyDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();

        String aboutCompany = getInfoAboutCompany(companyDTO);

        int monthNumber = LocalDate.now().getMonthValue();
        double salary = getSalaryForMonth(monthNumber);
        String info = "За " + monthNumber + " месяц: " + salary + " руб";

        double ordersSellSum = getOrdersForMonth(monthNumber).stream()
                .map(order -> order.getOrderPriceInfo().getTotalAmount())
                .mapToDouble(Double::doubleValue)
                .sum();

        tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(aboutCompany));
        tableCells.add(pdfUtils.getTableCell(info));
        tableCells.add(pdfUtils.getTableCell("ИТОГО: " + ordersSellSum + " руб."));

        return tableCells;
    }

    private List<Order> getOrdersForMonth(int currentMonth) {
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(), currentMonth, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);
        return orderRepository
                .findAllByOrderStatusAndAndDateOfLastUpdateIsBetween(
                        OrderStatus.COMPLETED,
                        startDate,
                        endDate);
    }

    private String getCityById(Long cityId) {
        return cityRepository.getOne(cityId).getCityName();
    }

    private double getSalaryForMonth(int monthNumber) {
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(), monthNumber, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);
        List<Florist> florists = floristRepository.findAllByDateOfCreationIsBetween(startDate, endDate);

        double sum = 0.0;

        for (Florist florist : florists) {
            double salary = florist.getSalary();
            List<Order> floristOrders = orderRepository
                    .findAllByOrderFloristInfoFloristIdAndOrderFloristInfoFloristCompletionTimeIsBetween(
                            florist.getId(),
                            startDate,
                            endDate);

            double bonus = 0.0;
            if (floristOrders.size() > 10) {
                bonus = salary * 0.5;
            }

            sum += salary + bonus;
        }

        return sum;
    }

    private String getInfoAboutCompany(CompanyDTO companyDTO) {
        List<Florist> florists = floristRepository.findAll();
        List<Shop> shops = shopRepository.findAll();
        return companyDTO.getName() +
                "\n" + companyDTO.getContacts().getPostalCode() + ", " +
                getCityById(companyDTO.getContacts().getCityId()) +
                ", " + companyDTO.getContacts().getAddress() +
                "\n" + companyDTO.getContacts().getFirstPhoneNumber() +
                ", " + companyDTO.getContacts().getEmail() +
                ", \n Кол-во магазинов: " + shops.size() +
                ", \n Кол-во сотрудников: " + florists.size();
    }

    private CompanyDTO getCompany() {
        return companyMapperDTO.toDto(companyRepository.findFirstBy());
    }
}
