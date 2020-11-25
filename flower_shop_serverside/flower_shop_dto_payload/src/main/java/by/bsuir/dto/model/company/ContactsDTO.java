package by.bsuir.dto.model.company;

import by.bsuir.dto.validation.annotation.EmptyOrValidPhone;
import by.bsuir.dto.validation.annotation.ValidEmail;
import by.bsuir.dto.validation.annotation.ValidPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class ContactsDTO {

    @NotNull
    @ValidPhone
    private String firstPhoneNumber;

    @EmptyOrValidPhone
    private String secondPhoneNumber;

    @ValidEmail
    private String email;

    @NotBlank(message = "Город не может быть пустым")
    @Size(max = 48, message = "Город не более 48 символов!")
    private String city;

    @NotBlank(message = "Адрес организации не может быть пустым")
    @Size(max = 48, message = "Адрес организации не более 48 символов!")
    private String address;
}
