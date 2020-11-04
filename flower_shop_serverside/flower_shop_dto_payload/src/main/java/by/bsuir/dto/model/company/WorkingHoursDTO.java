package by.bsuir.dto.model.company;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkingHoursDTO extends BaseAbstractDTO {

    private String hours;
}
