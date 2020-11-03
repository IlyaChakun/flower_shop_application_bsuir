package by.bsuir.dto.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractDTO extends BaseAbstractDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd hh.mm")
    private LocalDateTime dateOfCreation;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh.mm")
    private LocalDateTime dateOfLastUpdate;

}
