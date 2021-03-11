package by.bsuir.controller;

import by.bsuir.service.report.api.FloristReportService;
import by.bsuir.service.report.core.PdfExporter;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.mail.EmailService;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReportPdfControllerImpl implements ReportPdfController {

    private final PdfExporter pdfExporter;
    private final EmailService emailService;
    private final FloristReportService floristReportService;


    @Override
    public void exportFloristReport(String id,
                                    String monthNumber,
                                    Principal principal,
                                    HttpServletResponse response) throws DocumentException, IOException {
        Report report = floristReportService.getFloristMonthSalaryReport(principal.getName(), 1);

        emailService.sendReportOnMail(principal.getName(), report);

        response.setContentType(report.getContentType());
        final String headerKey = "Content-Disposition";
        final String headerValue = "attachment; filename=" + report.getFileName() + "_"
                + report.getDateOfCreation()
                + report.getFileSuffix();
        response.setHeader(headerKey, headerValue);

        pdfExporter.export(report, response.getOutputStream());
    }
}
