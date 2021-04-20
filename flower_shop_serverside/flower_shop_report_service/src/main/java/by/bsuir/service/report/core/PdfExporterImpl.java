package by.bsuir.service.report.core;

import by.bsuir.service.report.dto.Report;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public final class PdfExporterImpl implements PdfExporter {


    @Override
    public void export(Report report, OutputStream outputStream) throws DocumentException, IOException {
        doExport(report, outputStream);
    }

    private void doExport(final Report report,
                          final OutputStream outputStream) throws DocumentException {

        final Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();
        //add paragraph
        document.add(report.getParagraph());
        //create table
        final PdfPTable table = createPDFTable(report);
        //header
        writeTableHeader(table, report.getTableHeaders());
        //content
        writeTableData(table, report.getTableCells());
        //add table
        document.add(table);
        //add metadata
        addMetaData(report, document);
        //
        document.close();
    }

    private void writeTableHeader(final PdfPTable table,
                                  final List<PdfPCell> tableHeaders) {
        for (final PdfPCell headerCell : tableHeaders) {
            table.addCell(headerCell);
        }
    }


    private void writeTableData(final PdfPTable table,
                                final List<PdfPCell> tableCells) {
        for (final PdfPCell cell : tableCells) {
            table.addCell(cell);
        }
    }


    private void addMetaData(final Report report,
                             final Document document) {
        document.addTitle(report.getTitle());
        document.addSubject(report.getSubject());
        document.addKeywords(report.getKeywords());
        document.addAuthor(report.getAuthor());
        document.addCreator(report.getCreator());
    }


    private PdfPTable createPDFTable(final Report report) throws DocumentException {
        final float[] width = getWidth(report);
        final PdfPTable table = new PdfPTable(report.getNumColumns());
        table.setWidthPercentage(100f);
        table.setWidths(width);
        table.setSpacingBefore(report.getSpacing());

        return table;
    }

    private float[] getWidth(final Report report) {
        final int colCount = report.getNumsWidth().size();
        final float[] width = new float[colCount];
        for (int i = 0; i < colCount; i++) {
            width[i] = report.getNumsWidth().get(i);
        }
        return width;
    }


}
