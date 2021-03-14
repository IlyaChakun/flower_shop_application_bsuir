package by.bsuir.service.report.impl;

import by.bsuir.service.report.api.FloristReportService;
import by.bsuir.service.report.dto.Report;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FloristReportServiceImpl implements FloristReportService {
    @Override
    public Report getFloristMonthSalaryReport(String email, Integer monthNumber) {
        return null;
    }

    @Override
    public Report getFloristYearSalaryReport(String email) {
        return null;
    }

    @Override
    public Report getFloristOrdersReport(String email) {
        return null;
    }
}
