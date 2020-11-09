package by.bsuir.dto.model.company;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.validation.annotation.EmptyOrValidPhone;
import by.bsuir.dto.validation.annotation.ValidEmail;
import by.bsuir.dto.validation.annotation.ValidPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class ContactsDTO extends BaseAbstractDTO {

    @NotNull
    @ValidPhone
    private String firstPhoneNumber;

    @EmptyOrValidPhone
    private String secondPhoneNumber;

    @ValidEmail
    private String email;

    @NotBlank(message = "Город не может быть пустым")
    private String city;

    @NotBlank(message = "Адрес организации не может быть пустым")
    private String address;
}
