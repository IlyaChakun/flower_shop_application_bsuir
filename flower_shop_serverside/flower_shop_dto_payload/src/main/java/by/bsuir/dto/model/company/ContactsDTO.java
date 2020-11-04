package by.bsuir.dto.model.company;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactsDTO extends BaseAbstractDTO {

    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private String email;
    private String city;
    private String address;
}
