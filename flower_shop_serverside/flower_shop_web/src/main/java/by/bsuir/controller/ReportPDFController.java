package by.bsuir.controller;

import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.service.report.core.PdfExporter;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.mail.EmailService;
import by.bsuir.service.report.service.api.CompanyReportService;
import by.bsuir.service.report.service.api.DriverReportService;
import by.bsuir.service.report.service.api.OrderReportService;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReportPDFController {

    private final CompanyReportService companyReportService;
    private final OrderReportService orderReportService;
    private final DriverReportService driverReportService;
    private final PdfExporter pdfExporter;
    private final EmailService emailService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/admin/company-presentation/pdf")
    public void exportCarrierToPDF(Principal principal,
                                   HttpServletResponse response) throws DocumentException, IOException {

        System.out.println("admin here");
        String principalName = principal.getName();
        Report report = companyReportService.getCompanyPresentation(principalName);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/admin/company-drivers-report/pdf")
    public void exportCarrierDriversReportToPDF(Principal principal,
                                                HttpServletResponse response) throws DocumentException, IOException {

        String principalName = principal.getName();
        Report report = companyReportService.getCompanyDriversReport(principalName);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/admin/company-monthly-report/pdf")
    public void exportCarrierMonthlyReportToPDF(Principal principal,
                                                HttpServletResponse response) throws DocumentException, IOException {

        String principalName = principal.getName();
        Report report = companyReportService.getMonthlyReport(principalName);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/admin/company-annual-report/pdf")
    public void exportCarrierAnnualReportToPDF(Principal principal,
                                               HttpServletResponse response) throws DocumentException, IOException {

        String principalName = principal.getName();
        Report report = companyReportService.getAnnualReport(principalName);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }


    private void sendReportToEmail(String principalName, Report report) {
        emailService.sendReportOnMail(principalName, report);
    }
    private void setHeadersAndContentTypeToResponse(HttpServletResponse response, Report report) {
        response.setContentType(report.getContentType());
        //TODO fix russian language in file name - does not output in russian
        final String headerValue = "attachment; filename=" + report.getFileName() + "_"
                + report.getDateOfCreation().format(DateTimeFormatter.ISO_DATE)
                + report.getFileSuffix();

        response.setHeader("Content-Disposition", headerValue);
    }

    @GetMapping("/users/clients/personal-report/pdf")
    public void exportClientPersonalReportToPDF(Principal principal,
                                                HttpServletResponse response) throws DocumentException, IOException {

        String principalName = principal.getName();
        OrderDTO orderDTO = null;
        Report report = orderReportService.getClientOrderReport(orderDTO);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }

    @GetMapping("/users/drivers/personal-report/pdf")
    public void exportDriverPersonalReportToPDF(Principal principal,
                                                HttpServletResponse response) throws DocumentException, IOException {

        String principalName = principal.getName();
        Report report = driverReportService.getDriverPersonalReport(principalName);
        sendReportToEmail(principalName, report);
        setHeadersAndContentTypeToResponse(response, report);
        pdfExporter.export(report, response.getOutputStream());
    }

}
