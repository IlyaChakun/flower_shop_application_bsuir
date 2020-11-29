package by.bsuir.dto.model.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {

    private Long clientId;

    @Size(max = 512, message = "Комментарий не может быть более 512 символов")
    private String comment;

    @NotBlank(message = "Адресс не может быть пустым")
    private String address;

    @Min(value = 1, message = "Этаж должен быть  больше 1")
    @Max(value = 100, message = "Этаж должен быть меньше 100")
    private Integer floorNumber;

    @Min(value = 1, message = "номер подъезда должен быть больше 1")
    @Max(value = 20, message = "номер подъезда должен быть меньше 20")
    private Integer entranceNumber;//номер подъезда

}
