package by.bsuir.entity.user;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRole extends BaseAbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "display_name", nullable = false, unique = true)
    private String displayName;

    @Column(name = "is_displayed", nullable = false)
    private Boolean isDisplayed;

//    @ManyToMany(mappedBy = "userRoles")
//    private Set<User> users = new HashSet<>();

}
