package by.bsuir.flowershop.dto.user;

import by.bsuir.flowershop.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRoleDTO extends BaseAbstractDTO {

    public UserRoleDTO(String name) {
        this.name = name;
    }

    private String name;


    private String displayName;


    private Boolean isDisplayed;


}
