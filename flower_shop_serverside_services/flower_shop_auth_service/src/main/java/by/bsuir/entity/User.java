package by.bsuir.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "email", length = 128, unique = true)
    private String email;//username

    @Column(name = "password", length = 256)
    private String password;

//    @Column(name = "provider", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private SupportedAuthProvider provider;

    @OneToOne
    private UserRole userRole;

    @Column(name = "is_mail_confirmed")
    private boolean isMailConfirmed;

    @Column(name = "phone_number", length = 48)
    private String phoneNumber;

    private String userType;

//    private Image image;

    @PrePersist
    private void prePersist() {
        if (Objects.nonNull(userRole)) {
            this.userType = this.userRole.getName();
        }
    }

    /**********************/
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
