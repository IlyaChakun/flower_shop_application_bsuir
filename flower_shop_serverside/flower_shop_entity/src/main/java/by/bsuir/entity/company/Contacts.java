package by.bsuir.entity.company;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contacts extends BaseAbstractEntity {

    //first phone number
    @Column(name = "first_phone_number", length = 15)
    private String firstPhoneNumber;

    //second phone number
    @Column(name = "second_phone_number", length = 15)
    private String secondPhoneNumber;

    @Column(name = "email", length = 48)
    private String email;

    @Column(name = "city", length = 48)
    private String city;

    @Column(name = "address", length = 48)
    private String address;

}
