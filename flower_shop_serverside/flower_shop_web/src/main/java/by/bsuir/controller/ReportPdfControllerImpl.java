package by.bsuir.controller;

import by.bsuir.service.report.api.CompanyReportService;
import by.bsuir.service.report.api.FloristReportService;
import by.bsuir.service.report.core.PdfExporter;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.mail.EmailService;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ReportPdfControllerImpl implements ReportPdfController {

    private final PdfExporter pdfExporter;
    private final EmailService emailService;
    private final FloristReportService floristReportService;
    private final CompanyReportService companyReportService;

    @Override
    public void exportCompanyPresentationReport(
            Principal principal,
            HttpServletResponse response) throws DocumentException, IOException {

        Report report = companyReportService.getCompanyPresentationReport();
        emailService.sendReportOnMail(principal.getName(), report);
        composeHeaders(response, report);
    }

    @Override
    public void exportCompanyAnnualReport(
            Principal principal,
            HttpServletResponse response) throws DocumentException, IOException {
        Report report = companyReportService.getCompanyAnnualReport();
        emailService.sendReportOnMail(principal.getName(), report);
        composeHeaders(response, report);
    }

    @Override
    public void exportCompanyMonthlyReport(
            Principal principal,
            HttpServletResponse response) throws DocumentException, IOException {

        Report report = companyReportService.getCompanyMonthlyReport(LocalDate.now().getMonthValue());
        emailService.sendReportOnMail(principal.getName(), report);
        composeHeaders(response, report);
    }

    @Override
    public void exportFloristAnnualReport(String id,
            Principal principal,
            HttpServletResponse response) throws DocumentException, IOException {
        Report report = floristReportService.getFloristYearSalaryReport(principal.getName());

        emailService.sendReportOnMail(principal.getName(), report);

        composeHeaders(response, report);
    }

    @Override
    public void exportFloristMonthlyReport(String id,
            Principal principal,
            HttpServletResponse response) throws DocumentException, IOException {
        Report report = floristReportService.getFloristMonthSalaryReport(principal.getName(), LocalDate.now().getMonthValue());

        emailService.sendReportOnMail(principal.getName(), report);

        composeHeaders(response, report);
    }

    private void composeHeaders(HttpServletResponse response, Report report) throws IOException {
        response.setContentType(report.getContentType());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=" + report.getFileName() + "_"
                + report.getDateOfCreation()
                + report.getFileSuffix();
        response.setHeader(headerKey, headerValue);

        pdfExporter.export(report, response.getOutputStream());
    }
}
