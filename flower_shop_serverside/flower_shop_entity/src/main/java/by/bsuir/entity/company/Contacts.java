package by.bsuir.entity.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contacts {

    //first phone number
    @Column(name = "phone_number", length = 48)
    private String firstPhoneNumber;

    //second phone number
    @Column(name = "phone_number", length = 48)
    private String secondPhoneNumber;

    @Column(name = "city", length = 48)
    private String city;

    @Column(name = "address", length = 48)
    private String address;

    @Column(name = "licence_number", length = 48)
    private String licenceNumber;
}
