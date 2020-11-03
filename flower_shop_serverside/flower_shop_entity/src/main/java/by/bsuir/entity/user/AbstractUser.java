package by.bsuir.entity.user;

import by.bsuir.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractUser extends AbstractEntity {

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "email", length = 128, unique = true)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private SupportedAuthProvider provider;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "is_mail_confirmed")
    private boolean isMailConfirmed;

    @Column(name = "phone_number", length = 48)
    private String phoneNumber;

}
