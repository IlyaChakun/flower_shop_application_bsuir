package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.ImageDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
@NoArgsConstructor
public class ShopDTO extends AbstractDTO {

    @Valid
    private ContactsDTO contacts;

//    @JsonManagedReference
//    private CompanyDTO company;

    @Valid
    private WorkingHoursDTO workingHours;

    @Valid
    @JsonBackReference
    private List<AbstractFlowerProductDTO> shopProducts = new ArrayList<>();

    private ImageDTO image;
}
