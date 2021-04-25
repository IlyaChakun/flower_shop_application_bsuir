package by.bsuir.controller;

import by.bsuir.dto.validation.annotation.PositiveLong;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
public interface ReportPdfController {

    @GetMapping("/report/company/presentation/pdf")
    ResponseEntity<byte[]> exportCompanyPresentationReport() throws DocumentException, IOException;

    @GetMapping("/report/company/annual-report/pdf")
    ResponseEntity<byte[]> exportCompanyAnnualReport() throws DocumentException, IOException;

    @GetMapping("/report/company/monthly-report/pdf")
    ResponseEntity<byte[]> exportCompanyMonthlyReport() throws DocumentException, IOException;

    @GetMapping("/report/florists/{id}/annual-report/pdf")
    ResponseEntity<byte[]> exportFloristAnnualReport(
            @PathVariable("id") @PositiveLong String id) throws DocumentException, IOException;

    @GetMapping(path= "/report/florists/{id}/monthly-report/pdf")
    ResponseEntity<byte[]> exportFloristMonthlyReport(
            @PathVariable("id") @PositiveLong String id) throws DocumentException, IOException;
}
