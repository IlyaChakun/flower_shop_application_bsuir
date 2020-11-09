package by.bsuir.dto.model.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class WorkingHoursDTO {

    @NotBlank(message = "Поле рабочие часы не может быть пустым")
    private String hours;
}
