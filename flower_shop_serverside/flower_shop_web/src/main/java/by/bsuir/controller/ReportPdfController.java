package by.bsuir.controller;

import by.bsuir.dto.validation.annotation.PositiveLong;
import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

public interface ReportPdfController {

    @GetMapping("/florists/{id}/month/{monthNumber}/pdf")
     void exportFloristReport(@PathVariable("id") @PositiveLong String id,
                                    @PathVariable("monthNumber") @PositiveLong String monthNumber,
                                    Principal principal,
                                    HttpServletResponse response) throws DocumentException, IOException;

}
