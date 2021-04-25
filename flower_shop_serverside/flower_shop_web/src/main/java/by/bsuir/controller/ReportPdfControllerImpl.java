package by.bsuir.controller;

import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.service.api.CompanyService;
import by.bsuir.service.api.FloristService;
import by.bsuir.service.api.UserService;
import by.bsuir.service.report.api.CompanyReportService;
import by.bsuir.service.report.api.FloristReportService;
import by.bsuir.service.report.core.PdfExporter;
import by.bsuir.service.report.dto.Report;
import by.bsuir.service.report.mail.EmailService;
import com.lowagie.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ReportPdfControllerImpl implements ReportPdfController {

    private final PdfExporter pdfExporter;
    private final EmailService emailService;
    private final FloristService floristService;
    private final CompanyService companyService;
    private final UserService userService;
    private final FloristReportService floristReportService;
    private final CompanyReportService companyReportService;

    @Override
    public ResponseEntity<byte[]> exportCompanyPresentationReport() throws DocumentException, IOException {

        Report report = companyReportService.getCompanyPresentationReport();
        emailService.sendReportOnMail(getAdmin().getEmail(), report);

        HttpHeaders headers = composeHeaders(report);
        final byte[] bytes = getByteArrayFromReport(report);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @Override
    public ResponseEntity<byte[]> exportCompanyAnnualReport() throws DocumentException, IOException {
        Report report = companyReportService.getCompanyAnnualReport();
        emailService.sendReportOnMail(getAdmin().getEmail(), report);

        HttpHeaders headers = composeHeaders(report);
        final byte[] bytes = getByteArrayFromReport(report);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }


    @Override
    public ResponseEntity<byte[]> exportCompanyMonthlyReport() throws DocumentException, IOException {

        Report report = companyReportService.getCompanyMonthlyReport(LocalDate.now().getMonthValue());
        emailService.sendReportOnMail(getAdmin().getEmail(), report);

        HttpHeaders headers = composeHeaders(report);
        final byte[] bytes = getByteArrayFromReport(report);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @Override
    public ResponseEntity<byte[]> exportFloristAnnualReport(String id) throws DocumentException, IOException {
        Report report = floristReportService.getFloristYearSalaryReport(Long.parseLong(id));
        sendReportToUserByUserId(id, report);

        HttpHeaders headers = composeHeaders(report);
        final byte[] bytes = getByteArrayFromReport(report);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @Override
    public ResponseEntity<byte[]> exportFloristMonthlyReport(String id) throws DocumentException, IOException {
        Report report = floristReportService
                .getFloristMonthSalaryReport(Long.parseLong(id), LocalDate.now().getMonthValue());
        sendReportToUserByUserId(id, report);

        HttpHeaders headers = composeHeaders(report);
        final byte[] bytes = getByteArrayFromReport(report);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    private HttpHeaders composeHeaders(Report report) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + report.getFileName() + "_"
                + report.getDateOfCreation()
                + report.getFileSuffix());
        return headers;
    }


    private UserDTO getAdmin() {
        return userService.findAdminByUserId(companyService.findCompany().getAdminId());
    }

    private void sendReportToUserByUserId(String id, Report report) {
        FloristDTO floristDTO = floristService.findFloristByUserId(Long.parseLong(id));
        emailService.sendReportOnMail(floristDTO.getUser().getEmail(), report);
    }

    private byte[] getByteArrayFromReport(Report report) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfExporter.export(report, outputStream);
        return outputStream.toByteArray();
    }
}
