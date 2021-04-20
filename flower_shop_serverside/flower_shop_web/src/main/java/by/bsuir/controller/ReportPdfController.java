package by.bsuir.controller;

import by.bsuir.dto.validation.annotation.PositiveLong;
import com.lowagie.text.DocumentException;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
public interface ReportPdfController {

//    @GetMapping("/company/presentation/pdf")
//    void exportCompanyPresentationReport(
//            HttpServletResponse response) throws DocumentException, IOException;
//
//    @GetMapping("/company/annual-report/pdf")
//    void exportCompanyAnnualReport(
//            HttpServletResponse response) throws DocumentException, IOException;
//
//    @GetMapping("/company/monthly-report/pdf")
//    void exportCompanyMonthlyReport(
//            HttpServletResponse response) throws DocumentException, IOException;
//
//    @GetMapping("/florists/{id}/annual-report/pdf")
//    void exportFloristAnnualReport(@PathVariable("id") @PositiveLong String id,
//            HttpServletResponse response) throws DocumentException, IOException;

    @GetMapping(path= "/florists/{id}/monthly-report/pdf", produces = MediaType.)
    ResponseEntity<byte[]> exportFloristMonthlyReport(@PathVariable("id") @PositiveLong String id) throws DocumentException, IOException;
}
