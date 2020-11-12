package by.bsuir.dto.model.order;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveryAddressDTO extends BaseAbstractDTO {

    @NotBlank
    @Size(max = 128, message = "Адрес доставки не более 128 символов!")
    private String address;

    @NotBlank
    @Min(value = 0, message = "Номер этажа не может быть меньше 0!")
    private Short floorNumber;

    @NotBlank
    @Min(value = 0, message = "Номер подъезда не может быть меньше 0!")
    private Short entranceNumber;//номер подъезда

}
