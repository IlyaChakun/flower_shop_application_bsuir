package by.bsuir.flowershop.dto.user;

import by.bsuir.flowershop.dto.model.AbstractDTO;
import by.bsuir.flowershop.dto.model.common.ImageDTO;
import by.bsuir.flowershop.entity.user.SupportedAuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractDTO {

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private SupportedAuthProvider provider;

    private UserRoleDTO userRole;

    private boolean isMailConfirmed;

    private String phoneNumber;

    private String userType;

    private ImageDTO image;

}
