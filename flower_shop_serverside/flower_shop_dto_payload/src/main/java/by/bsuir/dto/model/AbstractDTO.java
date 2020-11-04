package by.bsuir.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
public abstract class AbstractDTO extends BaseAbstractDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfLastUpdate;

}
