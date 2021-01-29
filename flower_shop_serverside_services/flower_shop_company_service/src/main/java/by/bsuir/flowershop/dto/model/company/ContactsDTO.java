package by.bsuir.flowershop.dto.model.company;

import by.bsuir.flowershop.dto.validation.annotation.EmptyOrValidPhone;
import by.bsuir.flowershop.dto.validation.annotation.ValidEmail;
import by.bsuir.flowershop.dto.validation.annotation.ValidPhone;
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

    @NotBlank(message = "First phone number must be entered")
    @ValidPhone
    private String firstPhoneNumber;

    @EmptyOrValidPhone
    private String secondPhoneNumber;

    @NotBlank(message = "Email must be entered")
    @ValidEmail
    private String email;

    @NotNull(message = "City must be selected")
    private Long cityId;

    @NotBlank(message = "Адрес организации не может быть пустым")
    @Size(max = 48, message = "Адрес организации не более 48 символов!")
    private String address;

    @NotBlank(message = "Почтовый индекс не может быть пустым")
    @Size(max = 7, message = "Почтовый индекс  не более 7 символов!")
    private String postalCode;

}
