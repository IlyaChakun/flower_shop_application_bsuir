package by.bsuir.dto;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class FloristDTO extends AbstractDTO {

    @Valid
    private UserDTO user;

    @NotNull(message = "Shop id is required")
    private Long shopId; //флорист относится к конкретному магазину

    private Double experience;

    private Double salary;

}
