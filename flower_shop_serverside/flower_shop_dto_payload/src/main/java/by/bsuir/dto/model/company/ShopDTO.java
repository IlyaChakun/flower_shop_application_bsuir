package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.ImageDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ShopDTO extends AbstractDTO {

    @Valid
    private ContactsDTO contacts;

    @Valid
    private WorkingHoursDTO workingHours;

    @Valid
    @JsonIgnore
    private List<AbstractFlowerProductDTO> shopProducts = new ArrayList<>();

    private ImageDTO image;
}
