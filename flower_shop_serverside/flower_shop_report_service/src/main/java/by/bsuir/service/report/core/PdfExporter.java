package by.bsuir.service.report.core;

import by.bsuir.service.report.dto.Report;
import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;

public interface PdfExporter {

    void export(final Report report,
                final OutputStream outputStream) throws DocumentException, IOException;

}
