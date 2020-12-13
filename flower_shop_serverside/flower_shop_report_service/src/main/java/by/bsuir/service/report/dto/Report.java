package by.bsuir.service.report.dto;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    //paragraphs
    private Paragraph paragraph;
    //headers
    private List<PdfPCell> tableHeaders = new ArrayList<>();
    //table data
    private List<PdfPCell> tableCells = new ArrayList<>();

    //table settings
    private Integer numColumns = 0;//колво стобцов
    private List<Float> numsWidth = new ArrayList<>();
    private Float spacing = 10f;

    //metadata
    private String title = "";
    private String subject = "";
    private String keywords = "";
    private String author = "";
    private String creator = "";

    //file settings
    private String fileName;
    private String contentType;
    private String fileSuffix;

    //
    private LocalDateTime dateOfCreation;
    //content for mail
    private String content;
}
