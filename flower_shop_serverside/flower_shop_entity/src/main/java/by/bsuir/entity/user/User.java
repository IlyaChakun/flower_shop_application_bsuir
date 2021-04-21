package by.bsuir.entity.user;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "user_type")
public class User extends AbstractEntity {

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "email", length = 128, unique = true)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private SupportedAuthProvider provider;

    @OneToOne
    private UserRole userRole;

    @Column(name = "is_mail_confirmed")
    private boolean isMailConfirmed;

    @Column(name = "phone_number", length = 48)
    private String phoneNumber;

    private String userType;

    private Image image;

    @Column(name = "discount")
    private Integer discount;

    @PrePersist
    private void prePersist() {
        if (Objects.nonNull(userRole)) {
            this.userType = this.userRole.getName();
        }
    }
}
