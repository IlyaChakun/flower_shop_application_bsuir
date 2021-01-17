package by.bsuir.entity.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contacts {

    @Column(name = "first_phone_number", length = 15)
    private String firstPhoneNumber;

    @Column(name = "second_phone_number", length = 15)
    private String secondPhoneNumber;

    @Column(name = "email", length = 48)
    private String email;

    @Column(name = "city", length = 48)
    private String city;

    @Column(name = "address", length = 48)
    private String address;

    @Column(name="postal_code", length = 7)
    private String postalCode;

}
