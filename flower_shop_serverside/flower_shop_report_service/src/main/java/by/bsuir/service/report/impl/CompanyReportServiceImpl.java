package by.bsuir.service.report.impl;

import by.bsuir.service.report.api.CompanyReportService;
import by.bsuir.service.report.dto.Report;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CompanyReportServiceImpl implements CompanyReportService {
    @Override
    public Report getCompanyMonthlyReport(Integer monthNumber) {
        return null;
    }

    @Override
    public Report getCompanyAnnualReport() {
        return null;
    }

    @Override
    public Report getCompanyPresentationReport() {
        return null;
    }
}


//@Service
//@AllArgsConstructor
//public class CarrierReportServiceImpl implements CarrierReportService {
//
//    private final PdfUtils pdfUtils = new PdfUtils();
//    private final CarrierOwnerRepository carrierOwnerRepository;
//    private final CarrierOwnerMapperDTO carrierOwnerMapper;
//
//
//    @Override
//    public Report getCarrierCompanyPresentation(String email) {
//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        CarrierOwnerDTO carrierOwnerDTO = getCarrierOwnerByEmail(email);
//
//        Report report = new Report();
//
//        report.setFileName("Presentation - " + carrierOwnerDTO.getCompany().getOrganizationName());
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Карточка компании перевозчика");
//
//
//        pdfUtils.setTableSettings(report,
//                4,
//                Arrays.asList(14f, 5f, 7f, 5f),
//                10f);
//
//        report.setParagraph(pdfUtils.getParagraph(carrierOwnerDTO.getCompany().getOrganizationName()));
//
//        List<PdfPCell> tableHeaders = getPresentationTableHeaders();
//        report.setTableHeaders(tableHeaders);
//
//        List<PdfPCell> tableCells = getPresentationTableCells(carrierOwnerDTO);
//        report.setTableCells(tableCells);
//
//        pdfUtils.createMetadata(report, "Карточка компании",
//                carrierOwnerDTO.getLastName(), carrierOwnerDTO.getLastName(), "card", "Карточка компании");
//
//        return report;
//    }
//
//    private CarrierOwnerDTO getCarrierOwnerByEmail(String email) {
//        return carrierOwnerMapper.toDto(carrierOwnerRepository.getByEmail(email));
//    }
//
//
//    @Override
//    public Report getCarrierCompanyDriversReport(String email) {
//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        CarrierOwnerDTO carrierOwnerDTO = getCarrierOwnerByEmail(email);
//
//        Report report = new Report();
//
//        report.setFileName(carrierOwnerDTO.getCompany().getOrganizationName() + "_drivers report");
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Сводный отчет по капитанам компании");
//
//        pdfUtils.setTableSettings(report,
//                5,
//                Arrays.asList(2f, 15f, 5f, 4f, 6f),
//                10f);
//
//        report.setParagraph(pdfUtils.getParagraph("Сводный отчет по капитанам компании " + carrierOwnerDTO.getCompany().getOrganizationName()));
//
//        List<PdfPCell> driversTableHeaders = getDriversTableHeaders();
//        report.setTableHeaders(driversTableHeaders);
//
//        List<PdfPCell> driversTableCells = getDriversTableCells(carrierOwnerDTO.getCompany().getDrivers());
//        report.setTableCells(driversTableCells);
//
//        pdfUtils.createMetadata(report, "Сводный отчет по капитанам компании",
//                carrierOwnerDTO.getLastName(), carrierOwnerDTO.getLastName(), "driversCard", "Сводный отчет по капитанам компании");
//
//        return report;
//    }
//
//
//    @Override
//    public Report getCarrierCompanyTrucksReport(String email) {
//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        CarrierOwnerDTO carrierOwnerDTO = getCarrierOwnerByEmail(email);
//
//        Report report = new Report();
//
//        report.setFileName(carrierOwnerDTO.getCompany().getOrganizationName() + "_trucks report");
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Сводный отчет по флотилии компании");
//
//        pdfUtils.setTableSettings(report,
//                5,
//                Arrays.asList(2f, 14f, 4f, 6f, 4f),
//                10f);
//
//        report.setParagraph(pdfUtils.getParagraph("Сводный отчет по флотилии компании " + carrierOwnerDTO.getCompany().getOrganizationName()));
//
//        List<PdfPCell> trucksTableHeaders = getTrucksTableHeaders();
//        report.setTableHeaders(trucksTableHeaders);
//
//        List<PdfPCell> trucksTableCells = getTrucksTableCells(carrierOwnerDTO.getCompany().getTrucks());
//        report.setTableCells(trucksTableCells);
//
//        pdfUtils.createMetadata(report, "Сводный отчет по флотилии компании",
//                carrierOwnerDTO.getLastName(), carrierOwnerDTO.getLastName(), "trucksCard", "Сводный отчет по флотилии компании");
//
//        return report;
//    }
//
//
//    @Override
//    public Report getMonthlyReport(String email) {
//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        CarrierOwnerDTO carrierOwnerDTO = getCarrierOwnerByEmail(email);
//
//        Report report = new Report();
//
//        report.setFileName(carrierOwnerDTO.getCompany().getOrganizationName() + "_accounting monthly report");
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Сводный отчет по затратам компании за месяц");
//
//        pdfUtils.setTableSettings(report,
//                7,
//                Arrays.asList(2f, 12f, 4f, 4f, 4f, 4f, 4f),
//                10f);
//
//        report.setParagraph(pdfUtils.getParagraph("Сводный отчет по затратам компании " + carrierOwnerDTO.getCompany().getOrganizationName() + " за месяц."));
//
//        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
//        report.setTableHeaders(accountingReportHeaders);
//
//        List<PdfPCell> monthlyAccountingReportTableCells = getMonthAccountingReportTableCells(carrierOwnerDTO.getCompany());
//        report.setTableCells(monthlyAccountingReportTableCells);
//
//        pdfUtils.createMetadata(report, "Сводный отчет по затратам компании за месяц.",
//                carrierOwnerDTO.getLastName(), carrierOwnerDTO.getLastName(), "accountingMonthlyCard", "Сводный отчет по затратам компании за месяц.");
//
//        return report;
//    }
//
//
//    @Override
//    public Report getAnnualReport(String email) {
//        final String contentType = "application/pdf";
//        final String fileSuffix = ".pdf";
//
//        CarrierOwnerDTO carrierOwnerDTO = getCarrierOwnerByEmail(email);
//
//        Report report = new Report();
//
//        report.setFileName(carrierOwnerDTO.getCompany().getOrganizationName() + "_accounting annual report");
//
//        report.setContentType(contentType);
//        report.setFileSuffix(fileSuffix);
//        report.setDateOfCreation(LocalDateTime.now());
//        report.setContent("Сводный годовой отчет по затратам компании");
//
//        pdfUtils.setTableSettings(report,
//                7,
//                Arrays.asList(2f, 12f, 4f, 4f, 4f, 4f, 4f),
//                10f);
//
//        report.setParagraph(pdfUtils.getParagraph("Сводный годовой отчет по затратам компании " + carrierOwnerDTO.getCompany().getOrganizationName() + "."));
//
//        List<PdfPCell> accountingReportHeaders = getAccountingReportHeaders();
//        report.setTableHeaders(accountingReportHeaders);
//
//        List<PdfPCell> annualAccountingReportTableCells = getAnnualAccountingReportTableCells(carrierOwnerDTO.getCompany());
//        report.setTableCells(annualAccountingReportTableCells);
//
//        pdfUtils.createMetadata(report, "Сводный годовой отчет по затратам компании.",
//                carrierOwnerDTO.getLastName(), carrierOwnerDTO.getLastName(), "accountingMonthlyCard", "Сводный годовой отчет по затратам компании.");
//
//        return report;
//    }
//
//
//    private List<PdfPCell> getPresentationTableCells(CarrierOwnerDTO carrierOwnerDTO) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        //personal info
//
//        final String fio = "Представитель компании: \n" +
//                carrierOwnerDTO.getFirstName() + " " +
//                carrierOwnerDTO.getLastName() + " " +
//                carrierOwnerDTO.getPatronymic();
//
//        final String contacts = "Основной телефон: " + carrierOwnerDTO.getFirstPhoneNumber() +
//                "\nДоп: " + carrierOwnerDTO.getSecondPhoneNumber() +
//                "\nSkype: " + carrierOwnerDTO.getSkypeLogin() +
//                "\nEmail: " + carrierOwnerDTO.getEmail() +
//                "\nWeb: " + carrierOwnerDTO.getCompany().getPersonalSiteUrl();
//
//        final String organizationDate;
//
//        if (Objects.nonNull(carrierOwnerDTO.getCompany().getDateOfCreation())) {
//            organizationDate = "Дата основания компании: "
//                    + carrierOwnerDTO.getCompany().getDateOfCreation();
//        } else {
//            organizationDate = "Дата основания компании: не указана";
//        }
//
//
//        final String address = "Адрес: " +
//                carrierOwnerDTO.getCompany().getRegion().getRegionName() +
//                ", " + carrierOwnerDTO.getCompany().getAddress();
//
//
//        int count = carrierOwnerDTO.getCompany().getTrucks().size();
//        String size = "";
//        if (count == 0) {
//            size = "Нет кораблей";
//        } else if (count < 5) {
//            size = "до 5 кораблей";
//        } else if (count >= 10 && count < 20) {
//            size = "от 10 до 20 кораблей";
//        } else if (count >= 20 && count < 50) {
//            size = "от 20 до 50 кораблей";
//        } else if (count >= 50 && count < 100) {
//            size = "от 50 до 100 кораблей";
//        } else if (count >= 100) {
//            size = "свыше 100 кораблей";
//        }
//
//        final String truckParkSize = "Численность кораблей: " + size;
//
//
//        final String fullData = fio + "\n\n" +
//                contacts + "\n\n" +
//                organizationDate + "\n\n" +
//                address + "\n\n" +
//                truckParkSize;
//
//
//        tableCells.add(pdfUtils.getTableCell(fullData));
//
//
//        final StringBuilder logisticDirections = new StringBuilder();
//        carrierOwnerDTO.getCompany().getLogisticDirections()
//                .forEach(logisticDirection -> {
//                    logisticDirections.append(logisticDirection.getCountryNameRu());
//                    logisticDirections.append(", ");
//                });
//        logisticDirections.delete(logisticDirections.length() - 2, logisticDirections.length() - 1);
//        tableCells.add(pdfUtils.getTableCell(logisticDirections.toString()));
//
//        final StringBuilder logisticTypes = new StringBuilder();
//        carrierOwnerDTO.getCompany().getLogisticTypes()
//                .forEach(logisticType -> {
//                    logisticTypes.append(logisticType.getTypeName());
//                    logisticTypes.append(", ");
//                });
//        logisticTypes.delete(logisticTypes.length() - 2, logisticTypes.length() - 1);
//        tableCells.add(pdfUtils.getTableCell(logisticTypes.toString()));
//
//        final StringBuilder truckTypes = new StringBuilder();
//        carrierOwnerDTO.getCompany().getTrucks()
//                .forEach(truck -> {
//                    truckTypes.append(truck.getTruckType().getType());
//                    truckTypes.append(", ");
//                });
//        //        truckTypes.delete(truckTypes.length() - 2, truckTypes.length() - 1);
//        tableCells.add(pdfUtils.getTableCell(truckTypes.toString()));
//
//        return tableCells;
//    }
//
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
//    private List<PdfPCell> getTrucksTableCells(Set<ShipDTO> trucks) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        AtomicInteger count = new AtomicInteger();
//        //truck info
//        trucks.forEach(truck -> {
//            final String truckInfo =
//                    "Тип: " + truck.getTruckType().getType() +
//                            "\nГод выпуска: " + truck.getReleaseYear().format(DateTimeFormatter.ISO_DATE) +
//                            "\nГос. номер: " + truck.getPlateNumber() +
//                            "\nИд.номер: " + truck.getUniqueId();
//
//            final String truckSpecificationInfo =
//                    "Грузоподъемность: " + truck.getTruckSpecification().getWeightCapacity() + " т" +
//                            "\nГабариты (Д*Ш*В): " +
//                            truck.getTruckSpecification().getHeight() + "*" +
//                            truck.getTruckSpecification().getWidth() + "*" +
//                            truck.getTruckSpecification().getHeight() + " м" +
//                            "\nРасход топлива: " + truck.getTruckSpecification().getFuelConsumption() + " л/100км.";
//
//            final String mileage = truck.getMileage().toString() + " км";
//            final String truckWearPercentage = truck.getTruckWearPercentage().toString() + " %";
//            final String mileageAndWearPercentage = mileage + "\n" + truckWearPercentage;
//            final String isResigned = truck.getIsResigned() ? "списан" : "на ходу";
//
//
//            String driverInfo = " --- ";
//            if (Objects.nonNull(truck.getDriver())) {
//                if (!truck.getIsResigned()) {
//                    driverInfo = truck.getDriver().getFirstName() + "\n" +
//                            truck.getDriver().getLastName() + "\n" +
//                            truck.getDriver().getPatronymic() + "\n" +
//                            "Телефон: " + truck.getDriver().getFirstPhoneNumber();
//                }
//            }
//
//            final String fullData = truckInfo + "\n\n" +
//                    truckSpecificationInfo;
//
//            tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
//            tableCells.add(pdfUtils.getTableCell(fullData));
//            tableCells.add(pdfUtils.getTableCell(mileageAndWearPercentage));
//            tableCells.add(pdfUtils.getTableCell(driverInfo));
//            tableCells.add(pdfUtils.getTableCell(isResigned));
//
//        });
//
//        return tableCells;
//    }
//
//    private List<PdfPCell> getPresentationTableHeaders() {
//        List<PdfPCell> tableHeaders = new ArrayList<>();
//        tableHeaders.add(pdfUtils.getHeaderCell("Информация о компании"));
//        tableHeaders.add(pdfUtils.getHeaderCell("Направления перевозок"));
//        tableHeaders.add(pdfUtils.getHeaderCell("Виды перевозок"));
//        tableHeaders.add(pdfUtils.getHeaderCell("Подвижной состав"));
//        return tableHeaders;
//    }
//
//    private List<PdfPCell> getDriversTableHeaders() {
//        List<PdfPCell> driversTableHeaders = new ArrayList<>();
//        driversTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Персональная информация о работнике"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Стаж работы (лет)"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Ставка оплаты ($/час)"));
//        driversTableHeaders.add(pdfUtils.getHeaderCell("Закрепленный корабль"));
//        return driversTableHeaders;
//    }
//
//    private List<PdfPCell> getTrucksTableHeaders() {
//        List<PdfPCell> trucksTableHeaders = new ArrayList<>();
//        trucksTableHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
//        trucksTableHeaders.add(pdfUtils.getHeaderCell("Информация о корабле"));
//        trucksTableHeaders.add(pdfUtils.getHeaderCell("Пробег и % износа"));
//        trucksTableHeaders.add(pdfUtils.getHeaderCell("Данные капитана"));
//        trucksTableHeaders.add(pdfUtils.getHeaderCell("Состоит на учете"));
//        return trucksTableHeaders;
//    }
//
//
//    private List<PdfPCell> getAccountingReportHeaders() {
//        List<PdfPCell> monthlyAccountingReportHeaders = new ArrayList<>();
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("№ п/п"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Сводная информация"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Электроэнергия"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Отопление"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Охранные системы"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Зарплата работников"));
//        monthlyAccountingReportHeaders.add(pdfUtils.getHeaderCell("Итого"));
//        return monthlyAccountingReportHeaders;
//    }
//
//    private List<PdfPCell> getAnnualAccountingReportTableCells(CarrierCompanyDTO carrierCompanyDto) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        AtomicInteger count = new AtomicInteger();
//
//        CarrierCompanyChargesDTO companyCharges = carrierCompanyDto.getCharges();
//
//        double totalArea = carrierCompanyDto.getClosedCarrierCompanyArea() +
//                carrierCompanyDto.getOpenCarrierCompanyArea();
//
//        Double landTaxPaymentPerMonth = companyCharges.getLandTaxPerMonth() * totalArea;
//        Double securityGuardsPayment = companyCharges.getSecurityGuardsPaymentIncludingTaxesPerMonth() * companyCharges.getSecurityGuardsStaff();
//        //        Double dogCharges = companyCharges.getDogFoodCostPerMonth() * companyCharges.getDogStaff();
//        Double securitySystemPayment = companyCharges.getSecuritySystemMonthlyFee();
//        Double fireAlarmSystemPayment = companyCharges.getFireAlarmMonthlyFee();
//        //        Double gateServicePaymentPerHalfAYear = companyCharges.getGateServiceCostPerHalfAYear();
//        //        Double cleaningPaymentPerMonth = companyCharges.getCleaningCostPerMonth();
//
//
//        //        double securityFeesPerMonth = securitySystemPayment + gateServicePaymentPerHalfAYear + fireAlarmSystemPayment;
//        //        double paymentFeesPerMonth = securityGuardsPayment + dogCharges;
//
//        Double totalPerMonth;
//        Double coveredAreaLightingPaymentPerMonth;
//        Double openAreaLightingPaymentPerMonth;
//        Double lightingPaymentPerMonth;
//
//        double total = 0.0;
//        double totalLightingPayment = 0.0;
//        double totalHeatingPayment = 0.0;
//        double totalSecurityFees = 0.0;
//        double totalPaymentFees = 0.0;
//
//        Double heatingPaymentPerMonth = 0.0;
//        for (Month month : Month.values()) {
//            coveredAreaLightingPaymentPerMonth = getCoveredAreaLightingPaymentPerMonth(carrierCompanyDto, month);
//            openAreaLightingPaymentPerMonth = getOpenAreaLightingPaymentPerMonth(carrierCompanyDto, month);
//            heatingPaymentPerMonth = getHeatingPaymentPerMonth(carrierCompanyDto, month);
//
//            lightingPaymentPerMonth = coveredAreaLightingPaymentPerMonth + openAreaLightingPaymentPerMonth;
//
//            //            totalPerMonth = dogCharges + securityGuardsPayment +
//            totalPerMonth = securityGuardsPayment +
//                    securitySystemPayment + fireAlarmSystemPayment +
//                    //                    gateServicePaymentPerHalfAYear + cleaningPaymentPerMonth +
//                    landTaxPaymentPerMonth + lightingPaymentPerMonth + heatingPaymentPerMonth;//
//
//            String info = "Затраты на содержание в месяц: " +
//                    "\nНалог на землю: " + landTaxPaymentPerMonth + " руб" +
//                    "\nЗарплата охранников: " + securityGuardsPayment + " руб" +
//                    //                    "\nЗатраты по содержанию собак: " + dogCharges + " руб" +
//                    "\nОбслуживание охранной сигнализации: " + securitySystemPayment + " руб" +
//                    "\nОбслуживание пожарной сигнализации: " + fireAlarmSystemPayment + " руб" +
//                    //                    "\nОбслуживание ворот/шлагбаума: " + Math.ceil(gateServicePaymentPerHalfAYear / 6) + " руб" +
//                    //                    "\nУборка: " + cleaningPaymentPerMonth + " руб";
//
//                    tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
//            tableCells.add(pdfUtils.getTableCell(info));
//            tableCells.add(pdfUtils.getTableCell(lightingPaymentPerMonth.toString()));
//            tableCells.add(pdfUtils.getTableCell(heatingPaymentPerMonth.toString()));
//            //            tableCells.add(pdfUtils.getTableCell(Double.toString(securityFeesPerMonth)));
//            //            tableCells.add(pdfUtils.getTableCell(Double.toString(paymentFeesPerMonth)));
//            tableCells.add(pdfUtils.getTableCell(totalPerMonth.toString()));
//
//
//            total += totalPerMonth;
//            totalLightingPayment += lightingPaymentPerMonth;
//            totalHeatingPayment += heatingPaymentPerMonth;
//            //            totalSecurityFees += securityFeesPerMonth;
//            //            totalPaymentFees += paymentFeesPerMonth;
//        }
//
//        tableCells.add(pdfUtils.getTableCell(""));
//        tableCells.add(pdfUtils.getTableCell("ИТОГО: "));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(totalLightingPayment)));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(totalHeatingPayment)));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(totalSecurityFees)));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(totalPaymentFees)));
//        tableCells.add(pdfUtils.getTableCell(Double.toString(total)));
//
//        return tableCells;
//    }
//
//    private List<PdfPCell> getMonthAccountingReportTableCells(CarrierCompanyDTO carrierCompanyDto) {
//        List<PdfPCell> tableCells = new ArrayList<>();
//
//        AtomicInteger count = new AtomicInteger();
//
//        CarrierCompanyChargesDTO companyCharges = carrierCompanyDto.getCharges();
//        double totalArea = carrierCompanyDto.getClosedCarrierCompanyArea() +
//                carrierCompanyDto.getOpenCarrierCompanyArea();
//
//        Double landTaxPaymentPerMonth = companyCharges.getLandTaxPerMonth() * totalArea;
//        Double securityGuardsPayment = companyCharges.getSecurityGuardsPaymentIncludingTaxesPerMonth()
//                * companyCharges.getSecurityGuardsStaff();
//        //        Double dogCharges = companyCharges.getDogFoodCostPerMonth() * companyCharges.getDogStaff();
//        Double securitySystemPayment = companyCharges.getSecuritySystemMonthlyFee();
//        Double fireAlarmSystemPayment = companyCharges.getFireAlarmMonthlyFee();
//        //        Double gateServicePaymentPerHalfAYear = companyCharges.getGateServiceCostPerHalfAYear();
//        //        Double cleaningPaymentPerMonth = companyCharges.getCleaningCostPerMonth();
//
//
//        //        double securityFeesPerMonth = securitySystemPayment + gateServicePaymentPerHalfAYear + fireAlarmSystemPayment;
//        //        double paymentFeesPerMonth = securityGuardsPayment + dogCharges;
//
//        Double totalPerMonth;
//        Double coveredAreaLightingPaymentPerMonth;
//        Double openAreaLightingPaymentPerMonth;
//        Double lightingPaymentPerMonth;
//
//
//        Double heatingPaymentPerMonth = 0.0;
//
//        Month month = LocalDate.now().getMonth();
//
//
//        coveredAreaLightingPaymentPerMonth = getCoveredAreaLightingPaymentPerMonth(carrierCompanyDto, month);
//        openAreaLightingPaymentPerMonth = getOpenAreaLightingPaymentPerMonth(carrierCompanyDto, month);
//        heatingPaymentPerMonth = getHeatingPaymentPerMonth(carrierCompanyDto, month);
//
//        lightingPaymentPerMonth = coveredAreaLightingPaymentPerMonth + openAreaLightingPaymentPerMonth;
//
//        //        totalPerMonth = dogCharges + securityGuardsPayment +
//        totalPerMonth = securityGuardsPayment +
//                securitySystemPayment + fireAlarmSystemPayment +
//                //                gateServicePaymentPerHalfAYear + cleaningPaymentPerMonth +
//                landTaxPaymentPerMonth + lightingPaymentPerMonth + heatingPaymentPerMonth;//
//
//        String info = "Затраты на содержание за " + month.getDisplayName(TextStyle.FULL, new Locale("ru")) + " месяц: " +
//                "\nНалог на землю: " + landTaxPaymentPerMonth + " руб" +
//                "\nЗарплата охранников: " + securityGuardsPayment + " руб" +
//                //                "\nЗатраты по содержанию собак: " + dogCharges + " руб" +
//                "\nОбслуживание охранной сигнализации: " + securitySystemPayment + " руб" +
//                "\nОбслуживание пожарной сигнализации: " + fireAlarmSystemPayment + " руб" +
//                //                "\nОбслуживание ворот/шлагбаума: " + Math.ceil(gateServicePaymentPerHalfAYear / 6) + " руб" +
//                //                "\nУборка: " + cleaningPaymentPerMonth + " руб";
//
//                tableCells.add(pdfUtils.getTableCell(Integer.toString(count.incrementAndGet())));
//        tableCells.add(pdfUtils.getTableCell(info));
//        tableCells.add(pdfUtils.getTableCell(lightingPaymentPerMonth.toString()));
//        tableCells.add(pdfUtils.getTableCell(heatingPaymentPerMonth.toString()));
//        //        tableCells.add(pdfUtils.getTableCell(Double.toString(securityFeesPerMonth)));
//        //        tableCells.add(pdfUtils.getTableCell(Double.toString(paymentFeesPerMonth)));
//        tableCells.add(pdfUtils.getTableCell(totalPerMonth.toString()));
//
//
//        return tableCells;
//    }
//
//    private Double getCoveredAreaLightingPaymentPerMonth(CarrierCompanyDTO carrierCompanyDto, Month month) {
//
//        CarrierCompanyChargesDTO companyCharges = carrierCompanyDto.getCharges();
//        Double lightingMonthPayment = 0.0;
//
//        Double lightingWattageCost = companyCharges.getLightingWattageCost();
//        Double closedCarrierCompanyArea = carrierCompanyDto.getClosedCarrierCompanyArea();
//
//        //освещение считаем по сезонам, для летнего периода свой коэффициент, а для зимнего свой
//        switch (month) {
//
//            case OCTOBER:
//            case NOVEMBER:
//            case DECEMBER:
//            case JANUARY:
//            case FEBRUARY:
//            case MARCH: {
//                lightingMonthPayment =
//                        companyCharges.getLightingForCoveredAreaWinterCoefficient()
//                                * lightingWattageCost
//                                * closedCarrierCompanyArea;
//                break;
//            }
//
//            case APRIL:
//            case MAY:
//            case JUNE:
//            case JULY:
//            case AUGUST:
//            case SEPTEMBER: {
//                lightingMonthPayment =
//                        companyCharges.getLightingForCoveredAreaSummerCoefficient()
//                                * lightingWattageCost
//                                * closedCarrierCompanyArea;
//                break;
//            }
//
//        }
//        return lightingMonthPayment;
//    }
//
//    private Double getOpenAreaLightingPaymentPerMonth(CarrierCompanyDTO carrierCompanyDto, Month month) {
//
//        CarrierCompanyChargesDTO companyCharges = carrierCompanyDto.getCharges();
//        double lightingMonthPayment = 0.0;
//        Double lightingWattageCost = companyCharges.getLightingWattageCost();
//        Double openCarrierCompanyArea = carrierCompanyDto.getOpenCarrierCompanyArea();
//
//        //освещение считаем по сезонам, для летнего периода свой коэффициент, а для зимнего свой
//        switch (month) {
//            case OCTOBER:
//            case NOVEMBER:
//            case DECEMBER:
//            case JANUARY:
//            case FEBRUARY:
//            case MARCH: {
//                lightingMonthPayment =
//                        companyCharges.getLightingForOpenAreaWinterCoefficient()
//                                * lightingWattageCost
//                                * openCarrierCompanyArea;
//                break;
//            }
//
//            case APRIL:
//            case MAY:
//            case JUNE:
//            case JULY:
//            case AUGUST:
//            case SEPTEMBER: {
//                lightingMonthPayment =
//                        companyCharges.getLightingForOpenAreaSummerCoefficient()
//                                * lightingWattageCost
//                                * openCarrierCompanyArea;
//                break;
//            }
//
//        }
//        return lightingMonthPayment;
//    }
//
//    private Double getHeatingPaymentPerMonth(CarrierCompanyDTO carrierCompanyDto, Month month) {
//
//        CarrierCompanyChargesDTO companyCharges = carrierCompanyDto.getCharges();
//        double heatingMonthPayment = 0.0;
//        Double heatingWattageCost = companyCharges.getHeatingWattageCost();
//        Double closedCarrierCompanyArea = carrierCompanyDto.getClosedCarrierCompanyArea();
//
//        //отопление считаем только для зимнего периода
//        switch (month) {
//            case OCTOBER:
//            case NOVEMBER:
//            case DECEMBER:
//            case JANUARY:
//            case FEBRUARY:
//            case MARCH: {
//                heatingMonthPayment =
//                        companyCharges.getHeatingPremisesCoefficient()
//                                * heatingWattageCost
//                                * closedCarrierCompanyArea;
//                break;
//            }
//            default:
//                return heatingMonthPayment;
//        }
//        return heatingMonthPayment;
//    }
//
//}
