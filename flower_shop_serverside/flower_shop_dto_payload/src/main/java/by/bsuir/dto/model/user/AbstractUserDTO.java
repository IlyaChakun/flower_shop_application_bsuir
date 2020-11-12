package by.bsuir.dto.model.user;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.user.SupportedAuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class AbstractUserDTO extends AbstractDTO {


    private String name;


    private String email;

    @JsonIgnore
    private String password;


    private SupportedAuthProvider provider;


    private Set<RoleDTO> roles = new HashSet<>();


    private boolean isMailConfirmed;


    private String phoneNumber;

}
