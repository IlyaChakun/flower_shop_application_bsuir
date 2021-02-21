package by.bsuir.dto;

import by.bsuir.dto.user.UserSignUpRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class FloristRequestDTO {

    @Valid
    private UserSignUpRequest userSignUpRequest;

    private Double experience;

    @NotNull(message = "Shop id is required")
    private Long shopId;

}
