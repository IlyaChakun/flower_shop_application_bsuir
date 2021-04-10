package by.bsuir.service.report.impl;

import by.bsuir.service.report.dto.Report;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

final class PdfUtils {

    private static final String FONT = "src/main/resources/font/arial.ttf";

    PdfPCell getHeaderCell(final String cellData) {
        final int padding = 5;
        final int fontSize = 11;
        Font font = getFont();
        font.setSize(fontSize);

        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(cellData, font));//set data
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(padding);

        return cell;
    }

    PdfPCell getTableCell(final String cellData) {
        final int padding = 5;
        final int fontSize = 11;
        Font font = getFont();
        font.setSize(fontSize);

        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(cellData, font));//set data
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(padding);

        return cell;
    }

    Paragraph getParagraph(final String paragraphText) {
        final int fontSize = 12;

        Font font = getFont();
        font.setSize(fontSize);
        font.setColor(Color.BLACK);
//        font.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(paragraphText, font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        return paragraph;
    }

    Paragraph getParagraph(final String paragraphText, final int fontSize, Color color, int style, int alignment) {
        Font font = getFont();
        font.setSize(fontSize);
        font.setColor(color);
        font.setStyle(style);

        Paragraph paragraph = new Paragraph(paragraphText, font);
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    private Font getFont() {
        try {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(bf, 30, Font.NORMAL);
        } catch (IOException ex) {
            return FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        }
    }


    void createMetadata(final Report report,
                        final String title,
                        final String author,
                        final String creator,
                        final String keywords,
                        final String subject) {
        report.setTitle(title);
        report.setAuthor(author);
        report.setCreator(creator);
        report.setKeywords(keywords);
        report.setSubject(subject);
    }

    void setTableSettings(final Report report,
                          final Integer numColumns,
                          final List<Float> numsWidth,
                          final Float spacing) {
        report.setNumColumns(numColumns);
        report.getNumsWidth().addAll(numsWidth);
        report.setSpacing(spacing);
    }
}
