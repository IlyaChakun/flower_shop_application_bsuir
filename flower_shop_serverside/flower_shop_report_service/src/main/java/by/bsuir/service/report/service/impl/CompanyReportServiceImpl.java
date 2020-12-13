package by.bsuir.service.report.service.impl;


import by.bsuir.dto.mapper.user.ShopAdminMapperDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.dto.model.product.bouquet.FlowerBouquetDTO;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.repository.api.user.ShopAdminRepository;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.service.api.CompanyReportService;
import com.lowagie.text.pdf.PdfPCell;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyReportServiceImpl implements CompanyReportService {

    private final PdfUtils pdfUtils = new PdfUtils();
    private final ShopAdminMapperDTO shopAdminMapperDTO;
    private final ShopAdminRepository shopAdminRepository;


    @Override
    public Report getCompanyPresentation(String email) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        ShopAdminDTO shopAdmin = getCompanyAdminByEmail(email);

        Report report = new Report();

        report.setFileName("Presentation - " + shopAdmin.getCompany().getName());

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Карточка компании");


        pdfUtils.setTableSettings(report,
                4,
                Arrays.asList(14f, 5f, 7f, 5f),
                10f);

        report.setParagraph(pdfUtils.getParagraph(shopAdmin.getCompany().getName()));

        List<PdfPCell> tableHeaders = getPresentationTableHeaders();
        report.setTableHeaders(tableHeaders);

        List<PdfPCell> tableCells = getPresentationTableCells(shopAdmin);
        report.setTableCells(tableCells);

        pdfUtils.createMetadata(report, "Карточка компании",
                shopAdmin.getName(), shopAdmin.getName(), "card", "Карточка компании");

        return report;
    }

    private ShopAdminDTO getCompanyAdminByEmail(String email) {
        return shopAdminMapperDTO.toDto(shopAdminRepository.getByEmail(email));
    }


    @Override
    public Report getCompanyDriversReport(String email) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        ShopAdminDTO shopAdminDTO = getCompanyAdminByEmail(email);

        Report report = new Report();

        report.setFileName(shopAdminDTO.getCompany().getName() + "_drivers report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по перевозчикам компании");

        pdfUtils.setTableSettings(report,
                5,
                Arrays.asList(2f, 15f, 5f, 4f, 6f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Сводный отчет по перевозчикам компании " + shopAdminDTO.getCompany().getName()));

        List<PdfPCell> driversTableHeaders = getDriversTableHeaders();
        report.setTableHeaders(driversTableHeaders);

//        List<PdfPCell> driversTableCells = getDriversTableCells(shopAdminDTO.getCompany().getDrivers());
//        report.setTableCells(driversTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по перевозчикам компании",
                shopAdminDTO.getName(), shopAdminDTO.getName(), "driversCard", "Сводный отчет по перевозчикам компании");

        return report;
    }


    @Override
    public Report getMonthlyReport(String email) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        ShopAdminDTO shopAdminDTO = getCompanyAdminByEmail(email);

        Report report = new Report();

        report.setFileName(shopAdminDTO.getCompany().getName() + "_accounting monthly report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный отчет по затратам компании за месяц");

        pdfUtils.setTableSettings(report,
                7,
                Arrays.asList(2f, 12f, 4f, 4f, 4f, 4f, 4f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Сводный отчет по затратам компании " + shopAdminDTO.getCompany().getName() + " за месяц."));

        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
        report.setTableHeaders(accountingReportHeaders);

        List<PdfPCell> monthlyAccountingReportTableCells = getMonthAccountingReportTableCells(shopAdminDTO.getCompany());
        report.setTableCells(monthlyAccountingReportTableCells);

        pdfUtils.createMetadata(report, "Сводный отчет по затратам компании за месяц.",
                shopAdminDTO.getName(), shopAdminDTO.getName(), "accountingMonthlyCard", "Сводный отчет по затратам компании за месяц.");

        return report;
    }


    @Override
    public Report getAnnualReport(String email) {
        final String contentType = "application/pdf";
        final String fileSuffix = ".pdf";

        ShopAdminDTO shopAdminDTO = getCompanyAdminByEmail(email);

        Report report = new Report();

        report.setFileName(shopAdminDTO.getCompany().getName() + "_accounting annual report");

        report.setContentType(contentType);
        report.setFileSuffix(fileSuffix);
        report.setDateOfCreation(LocalDateTime.now());
        report.setContent("Сводный годовой отчет по затратам компании");

        pdfUtils.setTableSettings(report,
                7,
                Arrays.asList(2f, 12f, 4f, 4f, 4f, 4f, 4f),
                10f);

        report.setParagraph(pdfUtils.getParagraph("Сводный годовой отчет по затратам компании " + shopAdminDTO.getCompany().getName() + "."));

        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
        report.setTableHeaders(accountingReportHeaders);

        List<PdfPCell> annualAccountingReportTableCells = getAnnualAccountingReportTableCells(shopAdminDTO.getCompany());
        report.setTableCells(annualAccountingReportTableCells);

        pdfUtils.createMetadata(report, "Сводный годовой отчет по затратам компании.",
                shopAdminDTO.getName(), shopAdminDTO.getName(), "accountingMonthlyCard", "Сводный годовой отчет по затратам компании.");

        return report;
    }


    private List<PdfPCell> getPresentationTableCells(ShopAdminDTO shopAdminDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        final String fio = "Представитель компании: \n" + shopAdminDTO.getName();

        final String contacts = "Основной телефон: " + shopAdminDTO.getPhoneNumber() +
                "\nEmail: " + shopAdminDTO.getEmail();

        final String companyCretionDate;

        if (Objects.nonNull(shopAdminDTO.getCompany().getDateOfCreation())) {
            companyCretionDate = "Дата основания компании: "
                    + shopAdminDTO.getCompany().getDateOfCreation();
        } else {
            companyCretionDate = "Дата основания компании: не указана";
        }


        final String address = "Адрес: " +
                shopAdminDTO.getCompany().getContacts().getAddress() +
                ", " + shopAdminDTO.getCompany().getContacts().getCity();

        final String companyShops = "Магазины компании: " +
                shopAdminDTO.getCompany().getShops().stream()
                        .map(shop -> {
                            return shop.getContacts().getCity() + shop.getContacts().getAddress();
                        })
                        .collect(Collectors.joining(", ", "", ""));


        int count = shopAdminDTO.getCompany().getShops().size();
        String shopsAmount = "";
        if (count == 0) {
            shopsAmount = "Нет магазинов";
        } else if (count < 5) {
            shopsAmount = "до 5 магазинов";
        } else if (count >= 10 && count < 20) {
            shopsAmount = "от 10 до 20 магазинов";
        } else if (count >= 20 && count < 50) {
            shopsAmount = "от 20 до 50 магазинов";
        } else if (count >= 50 && count < 100) {
            shopsAmount = "от 50 до 100 магазинов";
        } else if (count >= 100) {
            shopsAmount = "свыше 100 магазинов";
        }

        final String fullData = fio + "\n\n" +
                contacts + "\n\n" +
                companyCretionDate + "\n\n" +
                address + "\n\n" +
                companyShops + "\n\n";


        tableCells.add(pdfUtils.getTableCell(fullData));
        tableCells.add(pdfUtils.getTableCell(shopsAmount));


        final StringBuilder flowerTypes = new StringBuilder();

        List<ShopDTO> shops = shopAdminDTO.getCompany().getShops();

        for (ShopDTO shop : shops) {
            List<AbstractFlowerProductDTO> products = shop.getShopProducts();

            for (AbstractFlowerProductDTO product : products) {

                if (product instanceof FlowerDTO) {
                    FlowerDTO flower = (FlowerDTO) product;
                    flowerTypes.append(flower.getFlowerType().getFlowerType());
                    flowerTypes.append(", ");
                }
                if (product instanceof FlowerBouquetDTO) {
                    FlowerBouquetDTO bouquet = (FlowerBouquetDTO) product;
                    flowerTypes.append(bouquet.getBouquetType().getBouquetType());
                    flowerTypes.append(", ");
                }
            }
        }

        tableCells.add(pdfUtils.getTableCell(flowerTypes.toString()));
        return tableCells;
    }


    private List<PdfPCell> getPresentationTableHeaders() {
        List<PdfPCell> tableHeaders = new ArrayList<>();
        tableHeaders.add(pdfUtils.getHeaderCell("Информация о компании"));
        tableHeaders.add(pdfUtils.getHeaderCell("Численность магазинов"));
        tableHeaders.add(pdfUtils.getHeaderCell("Виды цветов"));
        return tableHeaders;
    }


//    private List<PdfPCell> getDriversTableCells(Set<DriverDTO> drivers) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        AtomicInteger count = new AtomicInteger();
//        //driver info
//        drivers.forEach(driver -> {
//            final String fio = "ФИО капитана: \n" +
//                    driver.getFirstName() + " " +
//                    driver.getLastName() + " " +
//                    driver.getPatronymic();
//
//            final String secondPhone = driver.getSecondPhoneNumber() != null ? driver.getSecondPhoneNumber() : "";
//            final String skype = driver.getSkypeLogin() != null ? driver.getSkypeLogin() : "";
//
//
//            final String contacts = "Основной телефон: " + driver.getFirstPhoneNumber() +
//                    "\nДоп: " + secondPhone +
//                    "\nSkype: " + skype +
//                    "\nEmail: " + driver.getEmail();
//
//            final String uniqueId = "Идентификационный номер учета: " + driver.getUniqueId();
//            final String drivingExperience = driver.getDrivingExperience().toString();
//            final String paymentPerHour = driver.getDriverPayment().getPaymentPerHour().toString();
//
//            final String fullData = fio + "\n\n" +
//                    contacts + "\n\n" +
//                    uniqueId;
//
//            String truckInfo = "нет подходящего корабля";
//            if (Objects.nonNull(driver.getTruck())) {
//                ShipDTO truck = driver.getTruck();
//                truckInfo = "Гос. номер: " + truck.getPlateNumber() +
//                        "\nТип: " + truck.getTruckType().getType() +
//                        "\nИд.номер: " + truck.getUniqueId();
//            }
//
//            tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
//            tableCells.add(pdfUtils.getTableCell(fullData));
//            tableCells.add(pdfUtils.getTableCell(drivingExperience));
//            tableCells.add(pdfUtils.getTableCell(paymentPerHour));
//            tableCells.add(pdfUtils.getTableCell(truckInfo));
//
//        });
//
//        return tableCells;
//    }
//

    private List<PdfPCell> getDriversTableHeaders() {
        List<PdfPCell> driversTableHeaders = new ArrayList<>();
        driversTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        driversTableHeaders.add(pdfUtils.getHeaderCell("Персональная информация о работнике"));
        driversTableHeaders.add(pdfUtils.getHeaderCell("Стаж работы (лет)"));
        driversTableHeaders.add(pdfUtils.getHeaderCell("Ставка оплаты ($/час)"));
        driversTableHeaders.add(pdfUtils.getHeaderCell("Закрепленный корабль"));
        return driversTableHeaders;
    }


    private List<PdfPCell> getAccountingReportHeaders() {
        List<PdfPCell> monthlyAccountingReportHeaders = new ArrayList<>();
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Сводная информация"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Электроэнергия"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Отопление"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Охранные системы"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Зарплата работников"));
        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Итого"));
        return monthlyAccountingReportHeaders;
    }

    private List<PdfPCell> getAnnualAccountingReportTableCells(CompanyDTO companyDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();

        List<ShopDTO> companyShops = companyDTO.getShops();


//        double totalArea = companyDTO.getClosedCarrierCompanyArea() +
//                companyDTO.getOpenCarrierCompanyArea();


        Double totalPerMonth;
        Double coveredAreaLightingPaymentPerMonth;
        Double openAreaLightingPaymentPerMonth;
        Double lightingPaymentPerMonth;

        double total = 0.0;
        double totalLightingPayment = 0.0;
        double totalHeatingPayment = 0.0;
        double totalSecurityFees = 0.0;
        double totalPaymentFees = 0.0;

        Double heatingPaymentPerMonth = 0.0;
        for (Month month : Month.values()) {
//            coveredAreaLightingPaymentPerMonth = getCoveredAreaLightingPaymentPerMonth(companyDTO, month);
//            openAreaLightingPaymentPerMonth = getOpenAreaLightingPaymentPerMonth(companyDTO, month);
//            heatingPaymentPerMonth = getHeatingPaymentPerMonth(companyDTO, month);
//
//            lightingPaymentPerMonth = coveredAreaLightingPaymentPerMonth + openAreaLightingPaymentPerMonth;
//
////            totalPerMonth = dogCharges + securityGuardsPayment +
//            totalPerMonth = securityGuardsPayment +
//                    securitySystemPayment + fireAlarmSystemPayment +
////                    gateServicePaymentPerHalfAYear + cleaningPaymentPerMonth +
//                    landTaxPaymentPerMonth + lightingPaymentPerMonth + heatingPaymentPerMonth;//

            String info = "Затраты на содержание в месяц: " +
//                    "\nНалог на землю: " + landTaxPaymentPerMonth + " руб" +
//                    "\nЗарплата охранников: " + securityGuardsPayment + " руб" +
////                    "\nЗатраты по содержанию собак: " + dogCharges + " руб" +
//                    "\nОбслуживание охранной сигнализации: " + securitySystemPayment + " руб" +
//                    "\nОбслуживание пожарной сигнализации: " + fireAlarmSystemPayment + " руб" +
//                    "\nОбслуживание ворот/шлагбаума: " + Math.ceil(gateServicePaymentPerHalfAYear / 6) + " руб" +
//                    "\nУборка: " + cleaningPaymentPerMonth + " руб";

                    tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
            tableCells.add(pdfUtils.getTableCell(info));
//            tableCells.add(pdfUtils.getTableCell(lightingPaymentPerMonth.toString()));
            tableCells.add(pdfUtils.getTableCell(heatingPaymentPerMonth.toString()));
//            tableCells.add(pdfUtils.getTableCell(Double.toString(securityFeesPerMonth)));
//            tableCells.add(pdfUtils.getTableCell(Double.toString(paymentFeesPerMonth)));
//            tableCells.add(pdfUtils.getTableCell(totalPerMonth.toString()));


//            total += totalPerMonth;
//            totalLightingPayment += lightingPaymentPerMonth;
            totalHeatingPayment += heatingPaymentPerMonth;
//            totalSecurityFees += securityFeesPerMonth;
//            totalPaymentFees += paymentFeesPerMonth;
        }

        tableCells.add(pdfUtils.getTableCell(""));
        tableCells.add(pdfUtils.getTableCell("ИТОГО: "));
        tableCells.add(pdfUtils.getTableCell(Double.toString(totalLightingPayment)));
        tableCells.add(pdfUtils.getTableCell(Double.toString(totalHeatingPayment)));
        tableCells.add(pdfUtils.getTableCell(Double.toString(totalSecurityFees)));
        tableCells.add(pdfUtils.getTableCell(Double.toString(totalPaymentFees)));
        tableCells.add(pdfUtils.getTableCell(Double.toString(total)));

        return tableCells;
    }

    private List<PdfPCell> getMonthAccountingReportTableCells(CompanyDTO companyDTO) {
        List<PdfPCell> tableCells = new ArrayList<>();

        AtomicInteger count = new AtomicInteger();

//        CarrierCompanyChargesDTO companyCharges = companyDTO.getCharges();
//        double totalArea = companyDTO.getClosedCarrierCompanyArea() +
//                companyDTO.getOpenCarrierCompanyArea();
//
//        Double landTaxPaymentPerMonth = companyCharges.getLandTaxPerMonth() * totalArea;
//        Double securityGuardsPayment = companyCharges.getSecurityGuardsPaymentIncludingTaxesPerMonth()
//                * companyCharges.getSecurityGuardsStaff();
////        Double dogCharges = companyCharges.getDogFoodCostPerMonth() * companyCharges.getDogStaff();
//        Double securitySystemPayment = companyCharges.getSecuritySystemMonthlyFee();
//        Double fireAlarmSystemPayment = companyCharges.getFireAlarmMonthlyFee();
//        Double gateServicePaymentPerHalfAYear = companyCharges.getGateServiceCostPerHalfAYear();
//        Double cleaningPaymentPerMonth = companyCharges.getCleaningCostPerMonth();


//        double securityFeesPerMonth = securitySystemPayment + gateServicePaymentPerHalfAYear + fireAlarmSystemPayment;
//        double paymentFeesPerMonth = securityGuardsPayment + dogCharges;

        Double totalPerMonth;
        Double coveredAreaLightingPaymentPerMonth;
        Double openAreaLightingPaymentPerMonth;
        Double lightingPaymentPerMonth;


        Double heatingPaymentPerMonth = 0.0;

        Month month = LocalDate.now().getMonth();

//
//        coveredAreaLightingPaymentPerMonth = getCoveredAreaLightingPaymentPerMonth(companyDTO, month);
//        openAreaLightingPaymentPerMonth = getOpenAreaLightingPaymentPerMonth(companyDTO, month);
//        heatingPaymentPerMonth = getHeatingPaymentPerMonth(companyDTO, month);

//        lightingPaymentPerMonth = coveredAreaLightingPaymentPerMonth + openAreaLightingPaymentPerMonth;

//        totalPerMonth = dogCharges + securityGuardsPayment +
//        totalPerMonth = securityGuardsPayment +
//                securitySystemPayment + fireAlarmSystemPayment +
////                gateServicePaymentPerHalfAYear + cleaningPaymentPerMonth +
////                landTaxPaymentPerMonth + lightingPaymentPerMonth + heatingPaymentPerMonth;//
//                landTaxPaymentPerMonth  + heatingPaymentPerMonth;//

        String info = "Затраты на содержание за " + month.getDisplayName(TextStyle.FULL, new Locale("ru")) + " месяц: " +
//                "\nНалог на землю: " + landTaxPaymentPerMonth + " руб" +
//                "\nЗарплата охранников: " + securityGuardsPayment + " руб" +
////                "\nЗатраты по содержанию собак: " + dogCharges + " руб" +
//                "\nОбслуживание охранной сигнализации: " + securitySystemPayment + " руб" +
//                "\nОбслуживание пожарной сигнализации: " + fireAlarmSystemPayment + " руб" +
//                "\nОбслуживание ворот/шлагбаума: " + Math.ceil(gateServicePaymentPerHalfAYear / 6) + " руб" +
//                "\nУборка: " + cleaningPaymentPerMonth + " руб";

                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
        tableCells.add(pdfUtils.getTableCell(info));
//        tableCells.add(pdfUtils.getTableCell(lightingPaymentPerMonth.toString()));
        tableCells.add(pdfUtils.getTableCell(heatingPaymentPerMonth.toString()));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(securityFeesPerMonth)));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(paymentFeesPerMonth)));
//        tableCells.add(pdfUtils.getTableCell(totalPerMonth.toString()));


        return tableCells;
    }
}
