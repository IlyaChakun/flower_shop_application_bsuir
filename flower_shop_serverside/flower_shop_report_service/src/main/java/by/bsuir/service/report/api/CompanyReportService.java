package by.bsuir.service.report.api;

import by.bsuir.service.report.dto.Report;

public interface CompanyReportService {

    /**
     *  получить месячный отчет по  компании
     * @Param monthNumber
     * @return
     */
    Report getCompanyMonthlyReport(Integer monthNumber);

    /**
     * Отчет годовой  по компании
     */
    Report getCompanyAnnualReport();

    /**
     * Отчет по заказам МБ Сделать за определннывй период!
     */
    Report getCompanyPresentationReport();
}
