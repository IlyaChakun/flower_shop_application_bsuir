package by.bsuir.dto.model.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
public class WorkingHoursDTO {

    private String title;
}
