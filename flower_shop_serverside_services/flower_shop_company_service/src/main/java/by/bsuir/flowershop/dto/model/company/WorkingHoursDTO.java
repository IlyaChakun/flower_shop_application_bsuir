package by.bsuir.flowershop.dto.model.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class WorkingHoursDTO {

    @NotBlank(message = "Поле рабочие часы не может быть пустым")
    @Size(max = 15, message = "Рабочие часы не более 15 символов!")
    private String hours;
}
