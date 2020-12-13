package by.bsuir.service.report.service.api;

import by.bsuir.service.report.dto.Report;

public interface CompanyReportService {

    Report getCompanyPresentation(String email);

    Report getCompanyDriversReport(String email);

    Report getMonthlyReport(String email);

    Report getAnnualReport(String email);
}
