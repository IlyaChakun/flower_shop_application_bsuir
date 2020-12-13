package by.bsuir.service.report.service.api;


import by.bsuir.service.report.dto.Report;

public interface DriverReportService {

    Report getDriverPersonalReport(String email);
}
